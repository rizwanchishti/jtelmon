/*
 * wxAUIsample.cpp
 *
 *  Created on: Aug 19, 2012
 *      Author: Java
 */

// https://gist.github.com/90743


#include <wx/wx.h>
#include <wx/aui/aui.h>
#include <map>

class CursorView: public wxPanel
{
  wxTextCtrl *x_;
  wxTextCtrl *y_;

  public:
    CursorView(wxWindow* parent, wxWindowID id = wxID_ANY
        , const wxPoint& pos = wxDefaultPosition, const wxSize& size = wxDefaultSize
        , long style = wxTAB_TRAVERSAL, const wxString& name = wxT("panel"))
  : wxPanel(parent, id, pos, size, style, name)
  {
    wxBoxSizer *vbox=new wxBoxSizer(wxVERTICAL);
    {
      wxBoxSizer *hbox=new wxBoxSizer(wxHORIZONTAL);
      hbox->Add(new wxStaticText(this, wxID_ANY, wxT("x")));
      x_=new wxTextCtrl(this, wxID_ANY);
      hbox->Add(x_);
      vbox->Add(hbox);
    }
    {
      wxBoxSizer *hbox=new wxBoxSizer(wxHORIZONTAL);
      hbox->Add(new wxStaticText(this, wxID_ANY, wxT("y")));
      y_=new wxTextCtrl(this, wxID_ANY);
      hbox->Add(y_);
      vbox->Add(hbox);
    }
    SetSizer(vbox);
  }

  void OnMouseMotion(wxMouseEvent &event)
  {
    x_->SetValue(wxString::Format(wxT("%d"), event.GetX()));
    y_->SetValue(wxString::Format(wxT("%d"), event.GetY()));
    Refresh();
  }
};

class MyPanel : public wxPanel
{
  public:
    MyPanel(wxWindow* parent, wxWindowID id = wxID_ANY
        , const wxPoint& pos = wxDefaultPosition, const wxSize& size = wxDefaultSize
        , long style = wxTAB_TRAVERSAL, const wxString& name = wxT("panel"))
  : wxPanel(parent, id, pos, size, style, name)
  {
    SetFocus();
  }
};

class TopFrame : public wxFrame
{
  // menu
  wxMenuBar *menubar_;
  wxMenu *viewMenu_;

  // wxAui
  wxAuiManager aui_;
  std::map<int, wxString> paneMap_;

  public:
  TopFrame(const wxString& title)
    : wxFrame(NULL, wxID_ANY, title)
  {
    setupMenu_();
    setupAUI_();
  }

  ~TopFrame()
  {
    close_();
  }

  void OnQuit(wxCommandEvent& WXUNUSED(event))
  {
    close_();
  }

  void OnViewSelected(wxCommandEvent &event)
  {
    int id=event.GetId();
    wxAuiPaneInfo &pane=aui_.GetPane(paneMap_[id]);
    if(pane.IsShown()){
      pane.Show(false);
      viewMenu_->Check(id, false);
    }
    else{
      pane.Show(true);
      viewMenu_->Check(id, true);
    }
    aui_.Update();
  }

  void OnPaneClose(wxAuiManagerEvent &event)
  {
    for(std::map<int, wxString>::iterator it=paneMap_.begin();
        it!=paneMap_.end();
        ++it){
      if(event.pane->name==it->second){
        viewMenu_->Check(it->first, false);
      }
    }
  }

  private:
  void close_()
  {
    aui_.UnInit();
    Close(true);
  }

  void setupMenu_()
  {
    menubar_ = new wxMenuBar;
    viewMenu_ =  new wxMenu;
    menubar_->Append(viewMenu_, wxT("&View"));
    SetMenuBar(menubar_);
  }

  void setupAUI_()
  {
    MyPanel *motionPanel;
    {
      wxAuiPaneInfo info;
      wxString name(wxT("Center"));
      info.Name(name);
      info.Caption(name);
      info.Center();
      info.Floatable(false);
      info.CaptionVisible(false);
      motionPanel= new MyPanel(this, wxID_ANY);
      motionPanel->SetFocus();
      aui_.AddPane(motionPanel, info);
    }
    {
      wxAuiPaneInfo info;
      info.Left();
      info.MinSize(wxSize(100, 100));
      CursorView *viewPanel=new CursorView(this, wxID_ANY);
      addPane_(viewPanel, info, wxT("CursorView"));
      // fix
      motionPanel->Connect(wxEVT_MOTION
          , wxMouseEventHandler(CursorView::OnMouseMotion), NULL, viewPanel);
    }
    aui_.SetManagedWindow(this);
    aui_.Update();
    // fix
    aui_.Connect(wxEVT_AUI_PANE_CLOSE
        , wxAuiManagerEventHandler(TopFrame::OnPaneClose), NULL, this);
  }

  void addPane_(wxWindow *pane, wxAuiPaneInfo &info, const wxString &name)
  {
    // add aui pane
    info.Name(name);
    info.Caption(name);
    aui_.AddPane(pane, info);

    // toggle menu item
    wxMenuItem *item=viewMenu_->AppendCheckItem(wxID_ANY, name);
    item->Check(true);
    Connect(item->GetId(), wxEVT_COMMAND_MENU_SELECTED, wxCommandEventHandler(TopFrame::OnViewSelected));

    paneMap_.insert(std::make_pair(item->GetId(), name));
  }

};

class MyApp : public wxApp
{
  public:
    virtual bool OnInit()
    {
      ::wxInitAllImageHandlers();

      TopFrame *frame = new TopFrame(wxT("wxAUI sample"));
      frame->Show(true);

      return true;
    }
};

// IMPLEMENT_APP(MyApp)



