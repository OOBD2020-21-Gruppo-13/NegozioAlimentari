# NegozioAlimentari

Si sviluppi un sistema informativo, composto da una base di dati relazionale e un applicativo Java dotato di
GUI (Swing o JavaFX), per la gestione di un sistema di un negozio di ortofrutta. Il sistema deve permettere di
gestire le vendite e la disponibilità dei prodotti disponibili, identificando le tipologie (frutta, verdura,
farinacei, latticini, uova e confezionati). Ogni tipologia di prodotto ha delle caratteristiche diverse (ad
esempio, la frutta e la verdura hanno una data di raccolta, i latticini hanno sia una data di mungitura del latte
che una data di produzione, ecc..). Ogni cliente è registrato e ha una tessera punti associata che lo identifica
al fruttivendolo. Per ogni acquisto il cliente riceve il 10% del valore della spesa in punti fedeltà. Il sistema
deve premettere la ricerca dei clienti, differenziandoli sulla base delle categorie di prodotti acquistati e sulla
quantità di punti che hanno ottenuto per ogni categoria.

Per il gruppo da 3: il fruttivendolo ha un certo numero di dipendenti, e ad ogni vendita è associato il
dipendente che l’ha portata a termine. Deve pertanto essere possibile ricercare quale dipendente ha
effettuato il maggior numero di vendite in un determinato periodo differenziandolo dal dipendente che ha
portato al fruttivendolo il maggior introito.

# Collegarsi Al DBPostgres

Scaricare da qui il jar di postgres: https://jdbc.postgresql.org/download/postgresql-42.2.18.jar

Per installarlo su eclpise andare sul Java Project poi TastoDestro->Properties->Java Build Patch->Libraries->ModuelPatch->Add External Jar-> Selezionare il jar scaricato in precendenza.

