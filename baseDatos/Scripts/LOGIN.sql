--LOGIN
CREATE OR REPLACE PROCEDURE LOGIN
(USER IN USUARIOS.USUARIO%TYPE, PASS IN USUARIOS.PASSWORD%TYPE, idu out usuarios.usuario%type, idt out usuarios.TRABAJADORES_ID%type)
AS

NO_DATA_FOUND EXCEPTION;

BEGIN

SELECT usuario, TRABAJADORES_ID into idu, idt
FROM usuarios
WHERE usuario=user and PASSWORD=pass;

EXCEPTION 
WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR(-20002,'NO SE ENCUENTRAN REGISTROS.'); 
END LOGIN;

--PAQUETE LOGIN
CREATE OR REPLACE PACKAGE PLOGIN
AS
PROCEDURE VALIDACION (USER IN USUARIOS.USUARIO%TYPE, PASS IN USUARIOS.PASSWORD%TYPE, CAT OUT TRABAJADORES.CATEGORIA%TYPE);
PROCEDURE ACTUALIZAR (USER IN USUARIOS.USUARIO%TYPE, PASS IN USUARIOS.PASSWORD%TYPE, PASS2 IN USUARIOS.PASSWORD%TYPE, PASS3 IN USUARIOS.PASSWORD%TYPE);
END PLOGIN;

CREATE OR REPLACE PACKAGE BODY PLOGIN
AS 
PROCEDURE VALIDACION (USER IN USUARIOS.USUARIO%TYPE, PASS IN USUARIOS.PASSWORD%TYPE, CAT OUT TRABAJADORES.CATEGORIA%TYPE)
AS
P USUARIOS.PASSWORD%TYPE;
D TRABAJADORES.DNI%TYPE;
CLAVE_NO_VALIDA EXCEPTION;
BEGIN

SELECT PASSWORD INTO P
FROM USUARIOS WHERE USUARIO=USER AND PASSWORD=PASS;

SELECT DNI INTO D
FROM TRABAJADORES WHERE ID=(SELECT TRABAJADORES_ID FROM USUARIOS WHERE USUARIO=USER AND PASSWORD=PASS);

IF (P=D) THEN 
RAISE CLAVE_NO_VALIDA;
ELSE 
SELECT CATEGORIA INTO CAT
FROM TRABAJADORES
WHERE ID=(SELECT TRABAJADORES_ID FROM USUARIOS WHERE USUARIO=USER AND PASSWORD=PASS);
      
END IF;

EXCEPTION 
WHEN CLAVE_NO_VALIDA THEN 
    RAISE_APPLICATION_ERROR(-20001,'ACTUALIZA TU CONTRASEÑA.');
WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR(-20002,'NO SE ENCUENTRAN REGISTROS.'); 
END VALIDACION;

PROCEDURE ACTUALIZAR (USER IN USUARIOS.USUARIO%TYPE, PASS IN USUARIOS.PASSWORD%TYPE, PASS2 IN USUARIOS.PASSWORD%TYPE, PASS3 IN USUARIOS.PASSWORD%TYPE)
AS
P USUARIOS.PASSWORD%TYPE;
D TRABAJADORES.DNI%TYPE;
CLAVE_NO_VALIDA EXCEPTION;
NUEVA_CLAVE_NO_COINCIDE EXCEPTION;

BEGIN 
SELECT PASSWORD INTO P
FROM USUARIOS WHERE USUARIO=USER AND PASSWORD=PASS;

SELECT DNI INTO D
FROM TRABAJADORES WHERE ID=(SELECT TRABAJADORES_ID FROM USUARIOS WHERE USUARIO=USER AND PASSWORD=PASS);

IF (P=D) THEN 
RAISE CLAVE_NO_VALIDA;
END IF;
IF (PASS2!=PASS3) THEN
RAISE NUEVA_CLAVE_NO_COINCIDE;
END IF;
IF (PASS2=PASS3) THEN
UPDATE USUARIOS
SET PASSWORD=PASS2
WHERE USUARIO=USER;
END IF;

EXCEPTION
WHEN CLAVE_NO_VALIDA THEN
RAISE_APPLICATION_ERROR(-20001,'ACTUALIZA TU CONTRASEÑA.');
WHEN NUEVA_CLAVE_NO_COINCIDE THEN
RAISE_APPLICATION_ERROR(-20003,'NUEVA CLAVE NO COINCIDE.');
END ACTUALIZAR; 
END PLOGIN;