[Setup]
AppName=Snake Game
AppVersion=1.0
DefaultDirName={pf}\Snake Game
DefaultGroupName=Snake Game
OutputDir=..\release
OutputBaseFilename=SnakeGame_Installer
Compression=lzma
SolidCompression=yes
SetupIconFile="..\requirements\icon.ico"

[Files]
Source: "..\requirements\jdk-24_windows-x64_bin.exe"; DestDir: "{tmp}"; Flags: deleteafterinstall
Source: "..\release\SnakeGame.exe"; DestDir: "{app}"; Flags: ignoreversion

[Run]
Filename: "{tmp}\jdk-24_windows-x64_bin.exe"; Parameters: "/s"; StatusMsg: "Installing JDK 24..."; Flags: runhidden waituntilterminated
Filename: "{app}\SnakeGame.exe"; Parameters: ""; StatusMsg: "Launching Snake Game..."; Flags: nowait postinstall
