Option Explicit

Function IsJavaInstalled()
    Dim objShell, objFSO, strOutput

    ' Create a Shell object for running commands
    Set objShell = CreateObject("WScript.Shell")

    ' Run the "java -version" command and capture the output
    Set objFSO = CreateObject("Scripting.FileSystemObject")
    Set strOutput = objShell.Exec("java -version").StdOut

    ' Read the output of the command
    Do While Not strOutput.AtEndOfStream
        If InStr(strOutput.ReadLine, "java version") > 0 Then
            IsJavaInstalled = True
            Exit Function
        End If
    Loop

    IsJavaInstalled = False
End Function

Sub InstallJava()
    WScript.Echo "Java is not installed."

    ' Check the Windows version and take appropriate action
    If InStr(1, UCase(Trim(GetOSVersion())), "NT") > 0 Then
        WScript.Echo "Running on Windows NT/2000/XP"
        ' Perform Java installation steps for NT-based systems
        ' You would need to specify the installation steps here.
    Else
        WScript.Echo "Running on an unsupported Windows version"
    End If
End Sub

Function GetOSVersion()
    Dim objWMIService, colItems, objItem
    Set objWMIService = GetObject("winmgmts:\\.\root\cimv2")
    Set colItems = objWMIService.ExecQuery("Select * from Win32_OperatingSystem")

    For Each objItem in colItems
        GetOSVersion = objItem.Version
    Next
End Function

' Main script
If IsJavaInstalled() Then
    WScript.Echo "Java is already installed:"
    Call GetJavaVersion()
Else
    Call InstallJava()
End If

Sub GetJavaVersion()
    Dim objShell
    Set objShell = CreateObject("WScript.Shell")
    objShell.Run "java -version", 1, True
End Sub
