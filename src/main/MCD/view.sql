CREATE OR REPLACE VIEW CombinedRetourView AS
SELECT
    r.id_retour,
    r.description AS retour_description,
    r.id_composant AS id_composant_retour,
    tr.val AS type_retour_val,
    tr.id_type_retour AS id_type_retour,
    rd.id_reparation_details,
    rd.prix_reparation,
    rd.quantite_utilisee,
    re.id_reparation,
    re.date_reparation,
    re.description AS reparation_description,
    re.status AS reparation_status,
    o.id_ordinateur,
    o.id_type_ordi,
    o.marque,
    o.modele,
    c.nom AS client_name,
    c.email AS client_email
FROM
    Retour r
        JOIN
    type_retour_client tr ON r.id_type_retour = tr.id_type_retour
        JOIN
    ReparationDetails rd ON r.id_reparation_details = rd.id_reparation_details
        JOIN
    Reparation re ON rd.id_reparation = re.id_reparation
        JOIN
    Ordinateur o ON re.id_ordinateur = o.id_ordinateur
        JOIN
    Client c ON o.id_client = c.id_client;


CREATE OR REPLACE VIEW RecommandationComposantView AS
SELECT
    r.id_recommandation,
    r.mois,
    r.annee,
    r.id_composant,
    c.val AS composant_val,
    c.img AS composant_img
FROM
    Recommandation r
        JOIN
    Composant c ON r.id_composant = c.id_composant;


CREATE OR REPLACE VIEW RetourClientComposantView AS
SELECT
    r.id_retour,
    r.description AS retour_description,
    r.date_retour,
    c.id_client,
    c.nom AS client_nom,
    c.telephone AS client_telephone,
    c.email AS client_email,
    c.date_inscription AS client_date_inscription,
    comp.id_composant,
    comp.val AS composant_val,
    comp.img AS composant_img,
    comp.prix_achat AS composant_prix_achat,
    comp.prix_vente AS composant_prix_vente,
    tpr.id_type_retour,
    tpr.val AS type_retour_val
FROM Retour r
         INNER JOIN Client c ON r.id_client = c.id_client
         INNER JOIN Composant comp ON r.id_composant = comp.id_composant
         INNER JOIN type_retour_client tpr ON r.id_type_retour = tpr.id_type_retour;


CREATE OR REPLACE VIEW vue_commission_technicien AS
SELECT
    r.id_reparation,
    r.date_reparation,
    r.description AS description_reparation,
    r.status AS status_reparation,
    rd.id_reparation_details,
    rd.prix_reparation,
    rd.quantite_utilisee,
    e.id_employe,
    e.nom AS nom_employe,
    e.prenom AS prenom_employe,
    e.username AS employe_username,
    e.email AS employe_email,
    p.id_poste,
    p.val AS poste_val,
    (rd.prix_reparation * 0.05) AS commission_technicien
FROM
    Reparation r
        JOIN
    ReparationDetails rd ON r.id_reparation = rd.id_reparation
        JOIN
    Employe e ON rd.id_employe = e.id_employe
        JOIN
    Poste p ON e.id_poste = p.id_poste;


CREATE OR REPLACE VIEW vue_commission_technicien_genre AS
SELECT
    r.id_reparation,
    r.date_reparation,
    r.description AS description_reparation,
    r.status AS status_reparation,
    rd.id_reparation_details,
    rd.prix_reparation,
    rd.quantite_utilisee,
    e.id_employe,
    e.nom AS nom_employe,
    e.prenom AS prenom_employe,
    e.username AS employe_username,
    e.email AS employe_email,
    e.id_genre,
    CASE
        WHEN e.id_genre = 'GEN0001' THEN 'Femme'
        WHEN e.id_genre = 'GEN0002' THEN 'Homme'
        ELSE 'Non spécifié'
        END AS genre_employe,
    p.id_poste,
    p.val AS poste_val,
    (rd.prix_reparation * 0.05) AS commission_technicien
FROM
    Reparation r
        JOIN ReparationDetails rd ON r.id_reparation = rd.id_reparation
        JOIN Employe e ON rd.id_employe = e.id_employe
        JOIN Poste p ON e.id_poste = p.id_poste
WHERE
    r.date_reparation BETWEEN :date_debut AND :date_fin;


    CREATE OR REPLACE VIEW View_Prix_Composant AS
    SELECT
        pc.id_prix,
        pc.prix_composant,
        pc.date_prix,
        c.id_composant,
        c.val AS composant_val,
        c.img AS composant_img
    FROM
        Prix_Composant pc
            JOIN
        Composant c
        ON
            pc.id_composant = c.id_composant;
