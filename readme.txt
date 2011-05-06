Installationsanleitung
======================

Im vorliegenden Projekt befindet sich die Applikation "TodoApp", welche die Basis fuer den praktischen Teil des 
Workshops "Extending JEE 6 with Seam 3" auf der Jax 2011 bildet. "TodoApp" ist eine relativ kleine Applikation, 
in welcher aber diverse zentrale Anforderungen einer Enterprise-Applikation umgesetzt sind bzw. umgesetzt werden 
sollen. In der Basisvariante ist "TodoApp" dafuer zunaechst rein mit JEE 6 entwickelt, und wird dann im Laufe 
des Workshops mit immer mehr Seam 3 Komponenten versehen.

Die Teilnehmern können dieses am eigenen Rechner nachvollziehen - dafür ist es sinnvoll, "TodoApp" bereits 
eingerichtet zu haben und in der Basisvariante starten zu können.

Wir empfehlen(*) dazu die folgende Konstellation:
- JBoss AS 6.0 
- h2db
- Eclipse IDE (Plugins: Jboss Tools, m2eclipse Core+Extras(**), egit(***))
- git  

(*) JBoss AS 6.0 ist eine Muss-Voraussetzung
(**) Maven-Grundkenntnisse sind vorteilhaft
(***) git Kenntnisse sind nicht notwendig, lediglich zum Auschecken des Projekts wird einmalig git verwendet.

=======================================================================================================================

Zur Einrichtung:

[Die Anleitung wurde unter Linux erstellt. Windows-Benutzer verwenden bitte '\' statt '/' in Pfadangaben ;-)]

(1) JBoss AS 6 Final
- Download von http://sourceforge.net/projects/jboss/files/JBoss/JBoss-6.0.0.Final/
- Entpacken 
-> Der Verzeichnisname der JBoss-Installation wird im folgenden mit [jboss-install] bezeichnet
- Download Patch https://github.com/jangroth/jax2011/blob/master/patches/weld-core-no-jsf.jar
- weld-core-no-jsf.jar kopieren nach [jboss-install]/server/default/deployers/weld.deployer/ (Datei überschreiben)

(2) H2 Database Engine
- Download von http://www.h2database.com/html/main.html 
- Entpacken
- /h2/bin/h2-1.3.154.jar (oder ggf. aktuelleres JAR) kopieren nach [jboss-install]/common/lib

(3) Eclipse - Helios SR2 - IDE for Jave EE Developers
- Download von http://www.eclipse.org/downloads/
- Entpacken
- Installation JBoss Tools Plugin (Update Site: http://download.jboss.org/jbosstools/updates/stable/helios/)
- Installation m2eclipse Core Plugin (Update Site: http://m2eclipse.sonatype.org/sites/m2e)
- Installation m2eclipse Extras Plugin (Update Site: http://m2eclipse.sonatype.org/sites/m2e-extras)
- Optional: Installation egit Plugin (Update Site: http://download.eclipse.org/egit/updates)

(4) Installation git
[Git steht für alle populären Betriebssyteme zur Verfügung, Download zb hier http://git-scm.com/download]

(5) Auschecken des Projekts (Branch Master)
- git clone git@github.com:jangroth/jax2011.git
-> Der Verzeichnisname des Projekts wird im folgenden mit [projekt-dir] bezeichnet

(6) Projekt in Eclipse einrichten
- "Import/Maven/Existing Maven Project" aus [projekt-dir]/todo
- Kontextmenu auf Projekt: "Maven/Update Project Configuration"
- JBoss AS 6 in Eclipse einrichten ("Servers" View), Default Profil im JBoss auswählen
- /todo/src/main/resources/todoApp-ds.xml, Kontextmenu: "Mark as deployable"
- Projekt "todo" und "todoApp-ds.xml" zum JBoss hinzufügen
- JBoss starten, 
- Kontrolle im Browser: http://localhost:8080/todo zeigt Startseite von "TodoApp", 
- http://localhost:8080/todo/console zeigt Datenbank (URL: jdbc:h2:~/todoApp.db, User "sa", Password leer]  
- fertig :-)

(7) egit im Eclipse einrichten (optional)
- Kontextmenu auf Projekt: "Team/Share Project/Git/", Projekt auswählen, "finish"

=======================================================================================================================
Link zur Präsentation: http://prezi.com/swm0oao33igj/extending-jee-6-with-seam-3/


Fragen, Probleme, Fehler gefunden? Bitte kurze Mail an j(a)ngroth.de