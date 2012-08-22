/*
 *  http://www.wxwidgets.org/docs/tutorials/hello.htm
 */

// You have to include wxWidgets's header files, of course.
#include <wx/wx.h>

// Practically every app should define a new class derived from wxApp.
// By overriding wxApp's OnInit() the program can be initialized, e.g. by creating a new main window.
class MyApp : public wxApp
{
    virtual bool OnInit();
};

// As in all programs there must be a "main" function.
// Under wxWidgets MAIN is implemented using this macro, which creates an application instance and starts the program.
IMPLEMENT_APP(MyApp)

// The main window is created by deriving a class from wxFrame and giving it a menu and a status bar in its constructor.
// Also, any class that wishes to respond to any "event" (such as mouse clicks or messages from the menu or a button)
// must declare an event table using the macro below. Finally, the way to react to such events must be done in "handlers".
// In our sample, we react to two menu items, one for "Quit" and one for displaying an "About" window. These handlers should not be virtual.

class MyFrame : public wxFrame
{
public:
    MyFrame(const wxString& title, const wxPoint& pos, const wxSize& size);
    void OnQuit(wxCommandEvent& event);
    void OnAbout(wxCommandEvent& event);
};

// In order to be able to react to a menu command, it must be given a unique identifier such as a const or an enum.
enum
{
    ID_Quit=1,
    ID_About
};


// wxApp::OnInit() is called upon startup and should be used to initialize the program,
// maybe creating the main window .
// The frame should get a title bar text ("Hello World") and a position and start-up size.
// Returning TRUE indicates a successful intialization.
bool MyApp::OnInit()
{
    MyFrame *frame = new MyFrame( _("Hello Teleman"), wxPoint(50, 50),
                                  wxSize(450, 350));

    frame->Connect( ID_Quit, wxEVT_COMMAND_MENU_SELECTED,
                    (wxObjectEventFunction) &MyFrame::OnQuit );
    frame->Connect( ID_About, wxEVT_COMMAND_MENU_SELECTED,
                    (wxObjectEventFunction) &MyFrame::OnAbout );

    frame->Show(true);
    SetTopWindow(frame);
    return true;
}

// In the constructor of the main window (or later on) we create a menu with two menu items
// as well as a status bar to be shown at the bottom of the main window.
MyFrame::MyFrame(const wxString& title, const wxPoint& pos, const wxSize& size)
    : wxFrame( NULL, -1, title, pos, size )
{
    wxMenuBar *menuBar = new wxMenuBar;

    wxMenu *menuFile = new wxMenu;

    menuFile->Append( ID_About, _("&About...") );
    menuFile->AppendSeparator();
    menuFile->Append( ID_Quit, _("E&xit") );

    menuBar->Append(menuFile, _("&File") );

    SetMenuBar(menuBar);

    CreateStatusBar();

    SetStatusText( _("Poti sa faci asta sa mearga pe MacOS ?") );
}

// MyFrame::OnQuit() closes the main window by calling Close().
// the applicatin will quit.
void MyFrame::OnQuit(wxCommandEvent& WXUNUSED(event))
{
    Close(true);
}

// MyFrame::OnAbout() will display a small window with some text in it.
// In this case a typical "About" window with information about the program.
void MyFrame::OnAbout(wxCommandEvent& WXUNUSED(event))
{
    wxMessageBox( _("Faci sa mearga asta si pe Mac ?"),
                  _("Salutari Lucian ... "),
                  wxOK|wxICON_INFORMATION, this );
}


