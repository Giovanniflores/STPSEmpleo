(function() {
    $.support.cors = true;
    //Messages et libellés du formulaire
    var messages = {};
    var messagesFR = {
        general : {
            titre : 'Soumettre ma candidature',
            description : 'Entrez les informations suivantes pour soumettre votre candidature.',
            champsObligatoires : 'Champs obligatoires'
        },
        libelles : {
            prenom : 'Prénom',
            nom : 'Nom',
            cv : 'CV',
            sexe : 'Sexe',
            homme : 'Homme',
            femme : 'Femme',
            selectionnez : '- Sélectionnez -',
            nomUtil : 'Nom d\'utilisateur',
            courriel : 'Courriel',
            motDePasse : 'Mot de passe',
            confirmationMotDePasse : 'Confirmation du mot de passe',
            selectionnezFichier : 'Sélectionnez un fichier...',

            dateDisponibilite : 'Date de disponibilité',
            horaires : 'Horaires',
            langueCorrespondance : 'Langue de correspondance',
            francais : 'Français',
            anglais : 'Anglais',
            disponibilites : 'Disponibilités',
            reference : 'Référence',
            alerteEmploi : 'Alerte emploi',
            avisezMoi : 'Avisez-moi par courriel lorsqu\'un nouvel emploi correspondant à mon profil est affiché.',

            preferences : 'Préférences d\'emploi',
            domaine : 'Domaine d\'emploi',
            classe : 'Classe d\'emploi',
            specialisation : 'Spécialisation',
            typeEngagement : 'Type d\'engagement',

            lieuxTravailPreferes : 'Lieux de travail préférés',
            pays : 'Pays',
            province : 'Province',
            region : 'Région',
            lieuTravail : 'Lieu de travail',

            soumettre : 'Soumettre',
            annuler : 'Annuler'
        },
        erreurs : {
            prenomVide : 'Le champ "Prénom" doit contenir une valeur.',
            nomVide : 'Le champ "Nom" doit contenir une valeur.',
            courrielVide : 'Le champ "Courriel" doit contenir une valeur.',
            courrielInvalide : 'Le champ "Courriel" doit contenir une adresse valide.',
            sexeVide : 'Vous devez sélectionner un sexe.',
            CVVide : 'Vous devez attacher un CV à votre candidature.',
            CVFormat : 'Le format du fichier pour votre CV est invalide. Les formats acceptés sont : .doc, .docx, .pdf, .txt.',
            utilisateurVide : 'Le champ "Nom d\'utilisateur" doit contenir une valeur.',
            motdepasseVide : 'Le champ "Mot de passe" doit contenir une valeur.',
            motdepasseLongueur : 'Votre mot de passe doit contenir au moins 6 caractères.',
            confirmationVide : 'Le champ "Confirmation du mot de passe" doit contenir une valeur.',
            confirmationIdentique : 'Le champ "Confirmation du mot de passe" doit contenir la même valeur que le champ "Mot de passe".',
            connexion : 'Une erreur de connexion est survenue. Veuillez ré-essayer ultérieurement.',
            dateInvalide : 'Le champ "Date de disponibilité" doit contenir une date valide.',

            chargementHoraires : 'Une erreur est survenue lors du chargement des horaires.',
            chargementDisponibilites : 'Une erreur est survenue lors du chargement des disponibilités.',

            chargementDomainesEmploi : 'Une erreur est survenue lors du chargement des domaines d\'emploi.',
            chargementClassesEmploi : 'Une erreur est survenue lors du chargement des classes d\'emploi.',
            chargementSpecialisations : 'Une erreur est survenue lors du chargement des spécialisations.',
            chargementTypesEngagement : 'Une erreur est survenue lors du chargement des types d\'engagement.',

            chargementPays : 'Une erreur est survenue lors du chargement des pays.',
            chargementProvinces : 'Une erreur est survenue lors du chargement des provinces.',
            chargementRegions : 'Une erreur est survenue lors du chargement des régions.',
            chargementLieuxTravail : 'Une erreur est survenue lors du chargement des lieux de travail.'
        },
        succes : {
            candidatCree : 'Votre candidature a été enregistrée avec succès. Vous recevrez une confirmation par courriel.'
        }
    };

    var messagesEN = {
        general : {
            titre : 'Submit my application',
            description : 'Enter the following information to submit your application.',
            champsObligatoires : 'Required values'
        },
        libelles : {
            prenom : 'First name',
            nom : 'Last name',
            cv : 'CV',
            sexe : 'Sex',
            homme : 'Male',
            femme : 'Female',
            selectionnez : '- Select -',
            nomUtil : 'Username',
            courriel : 'Email',
            motDePasse : 'Password',
            confirmationMotDePasse : 'Confirm your password',
            selectionnezFichier : 'Select a file...',

            dateDisponibilite : 'Availability date',
            horaires : 'Work shifts',
            langueCorrespondance : 'Language of correspondence',
            francais : 'French',
            anglais : 'English',
            disponibilites : 'Availability',
            reference : 'Reference',
            alerteEmploi : 'Job alert',
            avisezMoi : 'Notify me by e-mail whenever a new job that matches my profile is posted.',

            preferences : 'Employment preferences',
            domaine : 'Job field',
            classe : 'Job class',
            specialisation : 'Specialization',
            typeEngagement : 'Type of commitment',

            lieuxTravailPreferes : 'Preferred job locations',
            pays : 'Country',
            province : 'Province',
            region : 'Region',
            lieuTravail : 'Work location',

            soumettre : 'Submit',
            annuler : 'Cancel'
        },
        erreurs : {
            prenomVide : 'The "First name" field must contain a value.',
            nomVide : 'The "Last name" field must contain a value.',
            courrielVide : 'The "Email" field must contain a value.',
            courrielInvalide : 'The "Email" field must contain a valid address.',
            sexeVide : 'You must select a sex.',
            CVVide : 'You must attach a CV to your application.',
            CVFormat : 'The file format of the document is invalid. Accept formats are : .doc, .docx, .pdf, .txt.',
            utilisateurVide : 'The "Username" field must contain a value.',
            motdepasseVide : 'The "Password" field must contain a value.',
            motdepasseLongueur : 'Your password must contain at least 6 characters.',
            confirmationVide : 'The "Confirm your password" field must contain a value.',
            confirmationIdentique : 'The "Confirm your password" field must be equal to the "Password" field.',
            connexion : 'A connexion error occured. Please try again later.',
            dateInvalide : 'The "Availabily date" field must contain a valid date.',

            chargementHoraires : 'An error occurred while loading the work shifts.',
            chargementDisponibilites : 'An error corred while loading the availabilities.',

            chargementDomainesEmploi : 'An error occurred while loading the job fields.',
            chargementClassesEmploi : 'An error occurred while loading the job classes.',
            chargementSpecialisations : 'An error occurred while loading the specializations.',
            chargementTypesEngagement : 'An error occurred while loading the types of commitment.',

            chargementPays : 'An error occurred while loading the countries.',
            chargementProvinces : 'An error occurred while loading the provinces.',
            chargementRegions : 'An error occurred while loading the regions.',
            chargementLieuxTravail : 'An error occurred while loading the work locations.'
        },
        succes : {
            candidatCree : 'Your application has been saved successfully. You will receive a confirmation by email soon.'
        }
    };

    //Selon la langue passée dans l'URL de l'iframe, affecter le bon objet de messages
    var langue = function() {
        var regexS = "[\\?&!]lang=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));

        if(results == null) {
            messages = messagesFR;
            return "fr";
        }
        else {
            if(results[1].toLowerCase() == 'fr') {
                messages = messagesFR;
            }
            else {
                messages = messagesEN;
            }
            return results[1].toLowerCase();
        }
    }();
    
    var user = function() {
        var regexS = "[\\&!]user=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var urlCompletResultat = function() {
        var regexS = "[\\&!]uri=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var domain = function() {
        var regexS = "[\\&!]domain=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var name = function() {
        var regexS = "[\\&!]name=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var email = function() {
        var regexS = "[\\&!]email=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var lastname = function() {
        var regexS = "[\\&!]lastname=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();
    
    var sexe = function() {
        var regexS = "[\\&!]sexe=([^&#!]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec(decodeURI(window.location.href).replace("%3D", "="));
        if (results == null) {
            return "";
        }else {
            return results[1];
        }
    }();

    //Indique si un fichier est actuellement sélectionné.
    var fichierSelectionne = false;

    //Fonction qui affiche un message de succès
    function afficherSucces(message) {
        $('#manitouAlertes').html('<i class="fa fa-check"></i>' + message).removeClass('manitouAlerteErreur').addClass('manitouAlerteSucces').show();
        $('#manitouFormulaire').addClass('manitouOverlay');
        $.ajax({
            url: domain + 'registro.do?method=keepQuebecApplication&quebec=on',
            dataType: 'json',
            success: function(data) {
            	window.location.replace("http://www.quebecentete.com/fr/travailler-a-quebec/offres-demploi/");
            },
            error: function() {
                afficherErreur(messages.erreurs.connexion);
            }
        });
    }

    //Fonction qui affiche un message d'erreur
    function afficherErreur(message) {
        $('#manitouAlertes').html('<i class="fa fa-exclamation-triangle"></i>' + message).removeClass('manitouAlerteSucces').addClass('manitouAlerteErreur').show().delay(4000).hide(400);
    }

    //Étendre jQuery pour permettre d'encadrer les champs en erreur
    $.fn.extend({
        encadrerErreur: function(message) {
            return this.each(function() {
                $(this).closest('td').addClass('manitouHighlight', 500).append('<div class="manitouErreur"><i class="fa fa-exclamation-triangle"></i> ' + message + '</div>')
            });
        }
    });

    //Fonction qui permet d'ajouter une préférence d'emploi
    function ajouterPreference() {
        $('#manitouPreferences tbody').append('<tr>' +
                                                    //Domaine d'emploi
                                                    '<td>' +
                                                        '<select class="domaineEmploi">' +
                                                            '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                            listeDomaines +
                                                        '</select>' +
                                                    '</td>' +

                                                    //Classe d'emploi
                                                    '<td>' +
                                                        '<select class="classeEmploi">' +
                                                            '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                        '</select>' +
                                                    '</td>' +

                                                    //Spécialisation
                                                    '<td>' +
                                                        '<select class="specialisation">' +
                                                            '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                        '</select>' +
                                                    '</td>' +

                                                    //Type d'engagement
                                                    '<td>' +
                                                        '<select class="typeEngagement">' +
                                                            '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                        '</select>' +
                                                    '</td>' +

                                                    //Bouton de suppression
                                                    '<td class="manitouSuppression" title="Supprimer">' +
                                                        '<i class="fa fa-trash-o"></i>' +
                                                    '</td>' +
                                                '</tr>');

        $('#manitouPreferences').show();
    }

    //Fonction qui permet d'ajouter un lieu de travail
    function ajouterLieuTravail() {
        $('#manitouLieuxPreferes tbody').append('<tr>' +
                                                        //Pays
                                                        '<td>' +
                                                            '<select class="pays">' +
                                                                '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                                listePays +
                                                            '</select>' +
                                                        '</td>' +

                                                        //Province
                                                        '<td>' +
                                                            '<select class="province">' +
                                                                '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                            '</select>' +
                                                        '</td>' +

                                                        //Région
                                                        '<td>' +
                                                            '<select class="region">' +
                                                                '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                            '</select>' +
                                                        '</td>' +

                                                        //Lieu de travail
                                                        '<td>' +
                                                            '<select class="lieuTravail">' +
                                                                '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                                            '</select>' +
                                                        '</td>' +

                                                        //Bouton de suppression
                                                        '<td class="manitouSuppression" title="Supprimer">' +
                                                            '<i class="fa fa-trash-o"></i>' +
                                                        '</td>' +
                                                    '</tr>');

        $('#manitouLieuxPreferes').show();
    }

    //Fonction qui valide le formulaire. Retourne true si toutes les validations ont passées.
    function validerFormulaire(donnees) {
        var erreur = false;

        //Enlever les messages d'erreur
        $('.manitouHighlight').removeClass('manitouHighlight');
        $('.manitouErreur').remove();

        //Utilisateur not null
        if($('#utilisateur').val().length == 0) {
            erreur = true;
            $('#utilisateur').encadrerErreur(messages.erreurs.utilisateurVide);
        }

        //Courriel not null
        if($('#courriel').val().length == 0) {
            erreur = true;
            $('#courriel').encadrerErreur(messages.erreurs.courrielVide);
        }
        else {
            //Courriel valide
            var regexCourriel = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
            if(!regexCourriel.test($('#courriel').val())) {
                erreur = true;
                $('#courriel').encadrerErreur(messages.erreurs.courrielInvalide);
            }
        }

        //Mot de passe not null et >= 6 caractères
        if($('#motdepasse').val().length == 0) {
            erreur = true;
            $('#motdepasse').encadrerErreur(messages.erreurs.motdepasseVide);
        }
        else if($('#motdepasse').val().length < 6) {
            erreur = true;
            $('#motdepasse').encadrerErreur(messages.erreurs.motdepasseLongueur);
        }

        //Confirmation du mot de passe not null
        if($('#confirmation').val().length == 0) {
            erreur = true;
            $('#confirmation').encadrerErreur(messages.erreurs.confirmationVide);
        }

        //Mot de passe = confirmation
        if($('#motdepasse').val().length > 0 || $('#confirmation').val().length > 0) {
            if($('#motdepasse').val() != $('#confirmation').val()) {
                erreur = true;
                $('#confirmation').encadrerErreur(messages.erreurs.confirmationIdentique);
            }
        }

        //Prénom not null
        if($('#prenom').val().length == 0) {
            erreur = true;
            $('#prenom').encadrerErreur(messages.erreurs.prenomVide);
        }

        //Nom not null
        if($('#nom').val().length == 0) {
            erreur = true;
            $('#nom').encadrerErreur(messages.erreurs.nomVide);
        }

        //Sexe not null
        /*if($('#sexe').val() != 'H' && $('#sexe').val() != 'F') {
            erreur = true;
            $('#sexe').encadrerErreur(messages.erreurs.sexeVide);
        }*/

        //Document not null et format adéquat
        if(fichierSelectionne) {
            var reg = /.+\.(doc|docx|pdf|txt)$/;

            if(!reg.test($('input[name=nomFichier]').val())) {
                erreur = true;
                $('#cv').encadrerErreur(messages.erreurs.CVFormat);
            }
        }
        else {
            erreur = true;
            $('#cv').encadrerErreur(messages.erreurs.CVVide);
        }
        
        //Date de disponibilité valide
        if($('#dateDisponibilite').val().length > 0) {
            if(isNaN(Date.parse($('#dateDisponibilite').val()))) {
                erreur = true;
                $('#dateDisponibilite').encadrerErreur(messages.erreurs.dateInvalide);
            }
        }

        window.scrollTo(0, 0);

        return !erreur;
    }

    //Fonction utilitaire qui vide le formulaire lorsqu'on clique sur Annuler
    function viderFormulaire() {
        //Enlever les messages à l'écran
        $('#manitouAlertes').html('').hide();
        $('.manitouHighlight').removeClass('manitouHighlight');
        $('.manitouErreur').remove();

        //Vider les champs
        $('#manitouSimplicite input:not([type=submit],[type=checkbox]), #manitouSimplicite select').val('');
        $('#manitouNomFichier').html('');

        //Décocher les cases à cocher
        $('#manitouSimplicite input[type=checkbox]').prop('checked', false);

        //Remettre des valeurs par défaut
        $('#langueCorrespondance').val(langue);
        $('#manitouPreferences tbody tr').remove();
        ajouterPreference();
        $('#manitouLieuxPreferes tbody tr').remove();
        ajouterLieuTravail();

        fichierSelectionne = false;
    }

    //Avant de construire le formulaire, il faut aller chercher certaines informations de pilotage :
    //Domaines d'emploi, pays, horaires et disponibilités
    //Ces calls ajax sont deferred. Le reste du traitement attendra que les 4 appels soient terminés avant de s'exécuter.
    var listeDomaines = '',
        listePays = '',
        listeHoraires = '',
        listeDisponibilites = '';

    //Appel AJAX pour aller chercher les domaines d'emploi de Manitou
    function chargerDomaines() {
        return $.ajax({
            url: urlServiceWeb +'domainesEmploi/' + langue,
            dataType: 'JSON',
            success: function(retour) {
                for(var i = 0; i < retour.items.length; i++) {
                    listeDomaines = listeDomaines + '<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>';
                }
            },
            error: function() {
                $('#manitouSimplicite').html(messages.erreurs.chargementDomainesEmploi);
            }
        });
    }

    //Appel AJAX pour aller chercher les pays de Manitou
    function chargerPays() {
        return $.ajax({
            url: urlServiceWeb +'pays/' + langue,
            dataType: 'JSON',
            success: function(retour) {
                for(var i = 0; i < retour.items.length; i++) {
                    listePays = listePays + '<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>';
                }
            },
            error: function() {
                $('#manitouSimplicite').html(messages.erreurs.chargementPays);
            }
        });
    }

    //Appel AJAX pour aller chercher les horaires de Manitou
    function chargerHoraires() {
        return $.ajax({
            url: urlServiceWeb +'horaires/' + langue,
            dataType: 'JSON',
            success: function(retour) {
                for(var i = 0; i < retour.items.length; i++) {
                    listeHoraires = listeHoraires + '<span class="manitouCheckbox"><input type="checkbox" id="horaire_' + i + '" class="manitouHoraire" value="' + retour.items[i].valeur + '"> <label for="horaire_' + i + '">' + retour.items[i].nom + '</label></span>';
                }
            },
            error: function() {
                $('#manitouSimplicite').html(messages.erreurs.chargementHoraires);
            }
        });
    }

    //Appel AJAX pour aller chercher les disponibilités de Manitou
    function chargerDisponibilites() {
        return $.ajax({
            url: urlServiceWeb +'disponibilites/' + langue,
            dataType: 'JSON',
            success: function(retour) {
                for(var i = 0; i < retour.items.length; i++) {
                    listeDisponibilites = listeDisponibilites + '<span class="manitouCheckbox"><input type="checkbox" id="disponibilite_' + i + '" class="manitouDisponibilite" value="' + retour.items[i].valeur + '"> <label for="disponibilite_' + i + '">' + retour.items[i].nom + '</label></span>';
                }
            },
            error: function() {
                $('#manitouSimplicite').html(messages.erreurs.chargementDisponibilites);
            }
        });
    }

    //Avant de continuer, il faut que tous les appels soient terminés
    $.when(chargerDomaines(), chargerPays(), chargerHoraires(), chargerDisponibilites()).then(function() {
        //Construction du formulaire
        $('#manitouSimplicite').html(
        '<div id="manitouAlertes"></div>' +
        '<form id="manitouFormulaire">' +
            '<h2>' + messages.general.titre + '</h2>' +
            '<p>' + messages.general.description + '</p>' +
            '<p class="manitouObligatoire">* ' + messages.general.champsObligatoires + '</p>' +
            '<input id="langueInterface" name="langueInterface" type="hidden" value="' + langue + '" />' +
            '<input id="sourceCandidat" name="sourceCandidat" type="hidden" value="' + identifiantSourceCandidat + '" />' +
            '<table class="manitouTable">' +
                //Nom utilisateur et courriel
                '<tr>' +
                    '<td>' +
                        '<label for="utilisateur">' + messages.libelles.nomUtil + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="utilisateur" name="utilisateur" type="text" value="' + user + '" size="30" maxlength="30" />' +
                    '</td>' +
                    '<td>' +
                        '<label for="courriel">' + messages.libelles.courriel + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="courriel" name="courriel" type="text" value="' + email + '" size="50" maxlength="250" />' +
                    '</td>' +
                '</tr>' +
                //Mot de passe et confirmation du mot de passe
                '<tr>' +
                    '<td>' +
                        '<label for="motdepasse">' + messages.libelles.motDePasse + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="motdepasse" name="motdepasse" type="password" value="" size="30" maxlength="30" />' +
                    '</td>' +
                    '<td>' +
                        '<label for="confirmation">' + messages.libelles.confirmationMotDePasse + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="confirmation" name="confirmation" type="password" value="" size="30" maxlength="30" />' +
                    '</td>' +
                '</tr>' +
                //Prénom et nom
                '<tr>' +
                    '<td>' +
                        '<label for="prenom">' + messages.libelles.prenom + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="prenom" name="prenom" type="text" value="' + name + '" size="50" maxlength="50" />' +
                    '</td>' +
                    '<td>' +
                        '<label for="nom">' + messages.libelles.nom + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="nom" name="nom" type="text" value="' + lastname + '" size="50" maxlength="50" />' +
                    '</td>' +
                '</tr>' +
                //Sexe et CV
                '<tr>' +
                    '<td>' +
                        '<label for="sexe">' + messages.libelles.sexe + '</label>' +
                        '<select id="sexe" name="sexe">' +
                            '<option value="">' + messages.libelles.selectionnez + '</option>' +
                            '<option selected="selected" value="F">' + messages.libelles.femme + '</option>' +
                            '<option value="H">' + messages.libelles.homme + '</option>' +
                        '</select>' +
                    '</td>' +
                    '<td>' +
                        '<label for="cv">' + messages.libelles.cv + ' <span class="manitouObligatoire">*</span></label>' +
                        '<input id="nomFichier" name="nomFichier" value="" type="hidden" /><br>' +
                        '<div class="manitouFichier">' +
                            '<i class="fa fa-file-text-o"></i> ' + messages.libelles.selectionnezFichier + '<span id="nomFichier"></span>' +
                            '<input id="cv" name="cv" type="file" value="" data-url="'+ urlServiceWeb +'creerCandidat_V3_2" />' +
                        '</div>' +
                        '<span id="manitouNomFichier"></span>' +
                    '</td>' +
                '</tr>' +
                //Date de disponibilité et horaire
                '<tr>' +
                    '<td>' +
                        '<label for="dateDisponibilite">' + messages.libelles.dateDisponibilite + '</label>' +
                        '<input id="dateDisponibilite" name="dateDisponibilite" type="text" size="10" maxlength="10" />' +
                    '</td>' +
                    '<td>' +
                        '<label for="horaires">' + messages.libelles.horaires + '</label>' +
                        '<input id="horaires" name="horaires" value="" type="hidden" /><br>' +
                        listeHoraires +
                    '</td>' +
                '</tr>' +
                //Langue de correspondance et disponibilités
                '<tr>' +
                    '<td>' +
                        '<label for="langueCorrespondance">' + messages.libelles.langueCorrespondance + '</label>' +
                        '<select id="langueCorrespondance" name="langueCorrespondance">' +
                            '<option value="fr" ' + (langue == 'fr' ? 'selected="selected"' : '') + '>' + messages.libelles.francais + '</option>' +
                            '<option value="en" ' + (langue == 'en' ? 'selected="selected"' : '') + '>' + messages.libelles.anglais + '</option>' +
                        '</select>' +
                    '</td>' +
                    '<td>' +
                        '<label for="disponibilites">' + messages.libelles.disponibilites + '</label>' +
                        '<input id="disponibilites" name="disponibilites" value="" type="hidden" /><br>' +
                        listeDisponibilites +
                    '</td>' +
                '</tr>' +
                //Référence et alertage
                '<tr>' +
                    '<td>' +
                        '<label for="reference">' + messages.libelles.reference + '</label>' +
                        '<input id="reference" name="reference" type="text" value="" size="50" maxlength="250" />' +
                    '</td>' +
                    '<td>' +
                        '<label for="alerteEmploi">' + messages.libelles.alerteEmploi + '</label><br>' +
                        '<span class="manitouCheckbox"><input id="alerteEmploi" name="alerteEmploi" value="O" type="checkbox" /> <label for="alerteEmploi">' + messages.libelles.avisezMoi + '</label></span>' +
                    '</td>' +
                '</tr>' +

                //Préférences d'emploi
                '<tr>' +
                    '<td colspan="2"><hr>' +
                        '<label>' + messages.libelles.preferences + '</label>' +
                        '<i id="manitouAjoutPreference" class="fa fa-plus-circle"></i>' + 
                        //IMPORTANT : 4 champs cachés qui contiennent la liste des domaines d'emploi, classes d'emploi, spécialisations et types d'engagement.
                        //            La liste des valeurs doit être séparée par un point-virgule (;). Pour le moment notre serveur ORDS ne peut pas recevoir
                        //            un array de valeurs avec le même nom (exemple name="domainesEmploi[]"). Juste avant de soumettre les données,
                        //            il faut mettre à jour ces champs cachés selon les données saisies dans le formulaire.
                        '<input id="domainesEmploi" name="domainesEmploi" type="hidden" value="0" />' +
                        '<input id="classesEmploi" name="classesEmploi" type="hidden" value="0" />' +
                        '<input id="specialisations" name="specialisations" type="hidden" value="0" />' +
                        '<input id="typesEngagement" name="typesEngagement" type="hidden" value="0" />' +

                        '<table id="manitouPreferences">' +
                            '<thead>' +
                            '<tr>' +
                                '<th>' + messages.libelles.domaine + '</th>' +
                                '<th>' + messages.libelles.classe + '</th>' +
                                '<th>' + messages.libelles.specialisation + '</th>' +
                                '<th>' + messages.libelles.typeEngagement + '</th>' +
                                '<th></th>' +
                            '</tr>' +
                            '</thead>' +

                            '<tbody>' +
                            '<tr>' +
                                //Domaine d'emploi
                                '<td>' +
                                    '<select class="domaineEmploi">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                        listeDomaines +
                                    '</select>' +
                                '</td>' +

                                //Classe d'emploi
                                '<td>' +
                                    '<select class="classeEmploi">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Spécialisation
                                '<td>' +
                                    '<select class="specialisation">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Type d'engagement
                                '<td>' +
                                    '<select class="typeEngagement">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Bouton de suppression
                                '<td class="manitouSuppression" title="Supprimer">' +
                                    '<i class="fa fa-trash-o"></i>' +
                                '</td>' +
                            '</tr>' +
                            '</tbody>' +
                        '</table>' +
                    '</td>' +
                '</tr>' +

                //Lieux de travail préférés
                '<tr>' +
                    '<td colspan="2"><hr>' +
                        '<label>' + messages.libelles.lieuxTravailPreferes + '</label>' +
                        '<i id="manitouAjoutLieuPrefere" class="fa fa-plus-circle"></i>' + 
                        //IMPORTANT : 4 champs cachés qui contiennent la liste des pays, provinces, régions et lieux de travail.
                        //            La liste des valeurs doit être séparée par un point-virgule (;). Pour le moment notre serveur ORDS ne peut pas recevoir
                        //            un array de valeurs avec le même nom (exemple name="pays[]"). Juste avant de soumettre les données,
                        //            il faut mettre à jour ces champs cachés selon les données saisies dans le formulaire.
                        '<input id="pays" name="pays" type="hidden" value="0" />' +
                        '<input id="provinces" name="provinces" type="hidden" value="0" />' +
                        '<input id="regions" name="regions" type="hidden" value="0" />' +
                        '<input id="lieuxTravail" name="lieuxTravail" type="hidden" value="0" />' +

                        '<table id="manitouLieuxPreferes">' +
                            '<thead>' +
                            '<tr>' +
                                '<th>' + messages.libelles.pays + '</th>' +
                                '<th>' + messages.libelles.province + '</th>' +
                                '<th>' + messages.libelles.region + '</th>' +
                                '<th>' + messages.libelles.lieuTravail + '</th>' +
                                '<th></th>' +
                            '</tr>' +
                            '</thead>' +

                            '<tbody>' +
                            '<tr>' +
                                //Pays
                                '<td>' +
                                    '<select class="pays">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                        listePays +
                                    '</select>' +
                                '</td>' +

                                //Province
                                '<td>' +
                                    '<select class="province">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Région
                                '<td>' +
                                    '<select class="region">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Lieu de travail
                                '<td>' +
                                    '<select class="lieuTravail">' +
                                        '<option selected="selected" value="0">' + messages.libelles.selectionnez + '</option>' +
                                    '</select>' +
                                '</td>' +

                                //Bouton de suppression
                                '<td class="manitouSuppression" title="Supprimer">' +
                                    '<i class="fa fa-trash-o"></i>' +
                                '</td>' +
                            '</tr>' +
                            '</tbody>' +
                        '</table>' +
                    '</td>' +
                '</tr>' +

                '<tr>' +
                    '<td class="manitouCentre" colspan="2">' +
                        '<button id="manitouAnnuler" class="manitouBouton">' + messages.libelles.annuler + '</button>' +
                        '<button id="manitouSoumettre" class="manitouBouton"><i class="fa fa-upload"></i> ' + messages.libelles.soumettre + '</button>' +
                    '</td>' +
                '</tr>' +
            '</table>' +
        '</form>');

        //Optionnel : indicateur visuel pour indiquer à l'utilisateur qu'il peut scroller
        var indicateurScroll = $('<div id="indicateurScroll"></div>');
        $('#manitouSimplicite').append(indicateurScroll);
        if($(window).scrollTop() + $(window).height() >= $(document).height() - 20) {
           indicateurScroll.css('opacity', '0');
        }

        $(window).scroll(function() {
           if($(window).scrollTop() + $(window).height() >= $(document).height() - 20) {
               indicateurScroll.css('opacity', '0');
           }
           else {
                indicateurScroll.css('opacity', '1');
           }
        });
        //Date picker pour la date de disponibilité
        $.datepicker.setDefaults( $.datepicker.regional[ langue ] );
        $('#dateDisponibilite').datepicker({dateFormat : 'yy-mm-dd'});

        //Vider le formulaire quand l'utilisateur clique sur Annuler
        $("#manitouAnnuler").click(function (e) {
            e.preventDefault();
            viderFormulaire();
        });

        //On valide le formulaire lorsque l'utilisateur clique sur le bouton Soumettre avant d'avoir sélectionné un fichier.
        //On est certain à ce moment qu'il y a au moins une erreur (le fichier n'est pas encore sélectionné).
        //Cette action est redéfinie lorsque l'utilisateur ajoute un fichier (voir plus bas).
        //Il s'agit d'une limitation du plugin jquery-file-upload qui ne permet pas de déclencher l'upload via un évènement externe.
        $("#manitouSoumettre").click(function (e) {
            e.preventDefault();
            validerFormulaire();
        });

        //Ajouter une ligne de préférence d'emploi
        $('#manitouAjoutPreference').on('click', function() {
            ajouterPreference();
        });

        //Enlever une préférence d'emploi quand l'utilisateur clique sur la corbeille
        $('#manitouPreferences').on('click', '.manitouSuppression', function() {
            $(this).closest('tr').remove();
            if($('#manitouPreferences tbody tr').length == 0) {
                $('#manitouPreferences').hide();
            }
        });

        //Ajouter un lieu de travail préféré
        $('#manitouAjoutLieuPrefere').on('click', function() {
            ajouterLieuTravail();
        });

        //Enlever un lieu de travail préféré quand l'utilisateur clique sur la corbeille
        $('#manitouLieuxPreferes').on('click', '.manitouSuppression', function() {
            $(this).closest('tr').remove();
            if($('#manitouLieuxPreferes tbody tr').length == 0) {
                $('#manitouLieuxPreferes').hide();
            }
        });

        //Rafraîchir les classes d'emploi et les types d'engagemnt lorsque l'utilisateur choisi un domaine d'emploi
        $('#manitouFormulaire').on('change', '.domaineEmploi', function() {
            var that = $(this);

            //Aller chercher les classes d'emploi associée à ce domaine
            $.ajax({
                url: urlServiceWeb +'classesEmploi/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovClasse = that.closest('tr').find('.classeEmploi');
                    lovClasse.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovClasse.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de classes d'emploi et déclancher l'event "change" pour rafraîchir en cascade.
                    lovClasse.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementClassesEmploi);
                }
            });

            //Puis les types d'engagement
            $.ajax({
                url: urlServiceWeb +'typesEngagement/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovEngagement = that.closest('tr').find('.typeEngagement');
                    lovEngagement.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovEngagement.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de types d'engagement
                    lovEngagement.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementTypesEngagement);
                }
            });
        });

        //Rafraîchir les spécialisations lorsque l'utilisateur choisi une classe d'emploi
        $('#manitouFormulaire').on('change', '.classeEmploi', function() {
            var that = $(this);

            //Aller chercher les spécialisations associées à cette classe d'emploi
            $.ajax({
                url: urlServiceWeb +'specialisations/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovSpecialisation = that.closest('tr').find('.specialisation');
                    lovSpecialisation.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovSpecialisation.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de spécialisations
                    lovSpecialisation.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementSpecialisations);
                }
            });
        });

        //Rafraîchir les provinces lorsque l'utilisateur choisi un pays
        $('#manitouFormulaire').on('change', '.pays', function() {
            var that = $(this);

            //Aller chercher les provinces associées à ce pays
            $.ajax({
                url: urlServiceWeb +'provinces/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovProvinces = that.closest('tr').find('.province');
                    lovProvinces.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovProvinces.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de provinces et déclancher l'event "change" pour rafraîchir en cascade.
                    lovProvinces.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementProvinces);
                }
            });
        });

        //Rafraîchir les provinces lorsque l'utilisateur choisi une province
        $('#manitouFormulaire').on('change', '.province', function() {
            var that = $(this);

            //Aller chercher les régions associées à cette province
            $.ajax({
                url: urlServiceWeb +'regions/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovRegions = that.closest('tr').find('.region');
                    lovRegions.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovRegions.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de régions et déclancher l'event "change" pour rafraîchir en cascade.
                    lovRegions.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementRegions);
                }
            });
        });

        //Rafraîchir les lieux de travail lorsque l'utilisateur choisi une région
        $('#manitouFormulaire').on('change', '.region', function() {
            var that = $(this);

            //Aller chercher les lieux de travail associées à cette région
            $.ajax({
                url: urlServiceWeb +'lieuxTravail/' + langue + '/' + $(this).val(),
                dataType: 'JSON',
                success: function(retour) {
                    var lovLieux = that.closest('tr').find('.lieuTravail');
                    lovLieux.find('option[value!=0]').remove();
                    
                    for(var i = 0; i < retour.items.length; i++) {
                        lovLieux.append('<option value="' + retour.items[i].valeur + '">' + retour.items[i].nom + '</option>');
                    }

                    //Réinitialiser la liste de lieux de travail
                    lovLieux.val('0').change();
                },
                error: function() {
                    afficherErreur(messages.erreurs.chargementLieuxTravail);
                }
            });
        });


        //Initialisation du plugin jQuery-File-Upload. L'URL à soumettre est contenue dans l'attribut data-url du champ CV.
        $('#cv').fileupload({
            maxNumberOfFiles: 1,
            dataType: 'json',
            forceIframeTransport: true,
            redirect: urlCompletResultat + '?',
            autoUpload: false,
            add: function (e, data) {
                fichierSelectionne = true;

                //Lorsque l'utilisateur ajoute un fichier, il faut redéfinir l'action du bouton Soumettre.
                //On sait maintenant que le champ de fichier contient quelque chose.
                $("#manitouSoumettre").off('click').on('click', function (e) {
                    e.preventDefault();

                    if(validerFormulaire(data)) {
                        //On désactive les boutons pour éviter la soumission en double
                        $('.manitouBouton').attr('disabled', 'disabled');
                        $('.manitouBouton').addClass('manitouInactif');

                        //Construire les données qui sont en tableaux en les concaténants avec le séparateur point-virgule.
                        $('#horaires').val($('.manitouHoraire:checked').map(function() { return this.value; }).get().join(';'));
                        $('#disponibilites').val($('.manitouDisponibilite:checked').map(function() { return this.value; }).get().join(';'));

                        $('#domainesEmploi').val($('.domaineEmploi').map(function() { return this.value; }).get().join(';'));
                        $('#classesEmploi').val($('.classeEmploi').map(function() { return this.value; }).get().join(';'));
                        $('#specialisations').val($('.specialisation').map(function() { return this.value; }).get().join(';'));
                        $('#typesEngagement').val($('.typeEngagement').map(function() { return this.value; }).get().join(';'));

                        $('#pays').val($('.pays').map(function() { return this.value; }).get().join(';'));
                        $('#provinces').val($('.province').map(function() { return this.value; }).get().join(';'));
                        $('#regions').val($('.region').map(function() { return this.value; }).get().join(';'));
                        $('#lieuxTravail').val($('.lieuTravail').map(function() { return this.value; }).get().join(';'));

                        $('#manitouFormulaire').addClass('manitouOverlay');

                        //Note : Le plugin jQuery-File-Upload permet d'envoyer des données supplémentaires dans le corps HTTP.
                        //On se sert de cette fonctionnalité pour envoyer toutes nos données dans le même appel au service web.
                        data.formData = $('#manitouFormulaire').serializeArray();
                        data.submit();
                    }
                });

                $("#manitouAnnuler").click(function (e) {
                    e.preventDefault();
                    data.files = [];
                    fichierSelectionne = false;
                });
                
                //Mettre à jour le nom du fichier
                $('#nomFichier').val(data.files[0].name);
                $('#manitouNomFichier').html(data.files[0].name)
            },
            done: function (e, retour) {
                $('.manitouErreur').remove();
                console.log('Done!');
                console.log(retour);
                if(retour.result.statut != 'OK') {
                    //En cas d'erreur, on les affiche à l'écran.
                    for(var i = 0; i < retour.result.messages.length; i++) {
                        $('#manitouFormulaire input[name=' + retour.result.messages[i].champ + '], #manitouFormulaire select[name=' + retour.result.messages[i].champ + ']').encadrerErreur(retour.result.messages[i].texte);
                    }

                    //Il faut aussi réactiver les boutons.
                    $('.manitouBouton').removeAttr('disabled');
                    $('.manitouBouton').removeClass('manitouInactif');
                    $('#manitouFormulaire').removeClass('manitouOverlay');
                }
                else {
                    afficherSucces(retour.result.messages[0]);
                }
            }
        });
    });
})();