Autori: Mattia Garavaglia, Pirovano Umberto
Università degli studi di Milano (UNIMI)

ASSIGNMENT 5
Data: 23/12/2021

Java project contenente l'esercizio richiesto per l'assignment 5 del corso di
ingegneria del software (edizione anno 2021/2022).

##############################################################################

Username e password registrati nel database per testing:

(elettore) username="mattia" password="1234"
(elettore) username="umberto" password="ciao"
(amministratore) username="admin" password="1234"

##############################################################################

Il pattern MVC è stato utilizzato per implementare le classi riguardanti la gui.
Classi MVC:
	(model) User , Amministratore, Elettore 
	(view) LoginWindowView  
	(controller) LoginWindowController

Il pattern DAO è stato utilizzato per implementare le interfacce e le classi per mezzo delle
quali l'applicazione comunica con il database.
Classi DAO:
	(interface) UserDAO, UserLoginDAO
	(concrete) UserDAOImpl, UserLoginDAOImpl
	(model/value) User , Amministratore, Elettore

##############################################################################

Le password vengono memorizzate nel database criptate con l'algoritmo SHA-512.

Il database è stato implementato tramite MySql.

Credenziali per l'accesso al database:
username= "root"
password= ""

