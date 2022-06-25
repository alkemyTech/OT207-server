-- It inserts the activities data

INSERT INTO activities (id, content, create_date_time, deleted, image, name, update_date_time)
SELECT 1,       
       'El espacio de apoyo escolar es el coraz�n del �rea educativa. Se realizan los
talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el
contraturno, Los s�bados tambi�n se realiza el taller para ni�os y ni�as que
asisten a la escuela doble turno. Tenemos un espacio especial para los de
1er grado 2 veces por semana ya que ellos necesitan atenci�n especial!
Actualmente se encuentran inscriptos a este programa 150 ni�os y ni�as de
6 a 15 a�os. Este taller est� pensado para ayudar a los alumnos con el
material que traen de la escuela, tambi�n tenemos una docente que les da
clases de lengua y matem�tica con una planificaci�n propia que armamos
en Manos para nivelar a los ni�os y que vayan con m�s herramientas a la
escuela.',       
       CURRENT_TIMESTAMP(),
       FALSE,
       'image',
       'Apoyo Escolar para el nivel Primario',
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=1);

INSERT INTO activities (id, content, create_date_time, deleted, image, name, update_date_time)
SELECT 2,
        'Del mismo modo que en primaria, este taller es el coraz�n del �rea
secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a
18 horas en el contraturno. Actualmente se encuentran inscriptos en el taller
50 adolescentes entre 13 y 20 a�os. Aqu� los j�venes se presentan con el
material que traen del colegio y una docente de la instituci�n y un grupo de
voluntarios los recibe para ayudarlos a estudiar o hacer la tarea. Este
espacio tambi�n es utilizado por los j�venes como un punto de encuentro y
relaci�n entre ellos y la instituci�n.',
       CURRENT_TIMESTAMP(),
       FALSE,
       'image',
       'Apoyo Escolar Nivel Secundaria',       
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=2);

INSERT INTO activities (id, content, create_date_time, deleted, image, name, update_date_time)
SELECT 3,
        'Es un programa destinado a j�venes a partir del tercer a�o de secundaria,
cuyo objetivo es garantizar su permanencia en la escuela y construir un
proyecto de vida que da sentido al colegio. El objetivo de esta propuesta es
lograr la integraci�n escolar de ni�os y j�venes del barrio promoviendo el
soporte socioeducativo y emocional apropiado, desarrollando los recursos
institucionales necesarios a trav�s de la articulaci�n de nuestras
intervenciones con las escuelas que los alojan, con las familias de los
alumnos y con las instancias municipales, provinciales y nacionales que
correspondan. El programa contempla:        
Encuentro semanal con tutores(Individuales y grupales)
Actividad proyecto (los participantes del
programa deben pensar una actividad relacionada a lo que quieren hacer una vez
terminada la escuela y su tutor los acompa�a en el proceso)
Ayudant�as (los que comienzan el 2do a�os del programa deben
elegir una de las actividades que se realizan en la instituci�n para acompa�arla
e ir conociendo como es el mundo laboral que les espera).
Acompa�amiento escolar y familiar (Los tutores son encargados de
articular con la familia y con las escuelas de los j�venes para
monitorear el estado de los tutorados)
Beca est�mulo (los j�venes reciben una beca est�mulo que es
supervisada por los tutores). Actualmente se encuentran inscriptos en
el programa 30 adolescentes.
Taller Arte y Cuentos: Taller literario y de manualidades que se realiza
semanalmente.
Paseos recreativos y educativos: Estos paseos est�n pensados para
promover la participaci�n y sentido de pertenencia de los ni�os, ni�as
y adolescentes al �rea educativa.',
       CURRENT_TIMESTAMP(),
       FALSE,
       'image',
       'Tutor�as',       
       NULL WHERE NOT EXISTS (SELECT * FROM activities WHERE id=3);