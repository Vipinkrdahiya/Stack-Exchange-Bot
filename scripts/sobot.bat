@ECHO OFF
:checki
ping www.google.com -n 1 -w 1000
IF errorlevel 1 (
    GOTO :checki
) ELSE (
    SET DD=%date:~-7,2%
    SET MM=%date:~-10,2%
    SET YY=%date:~-4%
    SET DATELOGGER=""
    SET JARFILE=""
    SET CONFIGFILE=""
    IF NOT EXIST %DATELOGGER% (
        ECHO %DD%-%MM%-%YY% > %DATELOGGER%
        java -jar %JARFILE% %CONFIGFILE%   
    ) ELSE (
        SET /p LASTRUNDATE=<%DATELOGGER%
        IF NOT %LASTRUNDATE% == %DD%-%MM%-%YY% (
            java -jar %JARFILE% %CONFIGFILE%
        )
    ) 
)
