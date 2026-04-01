pipeline {
    agent any

    tools {
        // Doit correspondre au nom configuré dans "Global Tool Configuration"
        maven 'M2_HOME' 
    }

    stages {
        stage('Checkout code') {
            steps {
                // Récupération du code depuis votre dépôt GitHub
                git branch: 'master', url: 'https://github.com/nadabenahmed/CountryRepo.git' [cite: 46]
            }
        }

        stage('Compile code') {
            steps {
                sh 'mvn clean compile' [cite: 52]
            }
        }

        stage('Test code') {
            steps {
                // Exécution des tests JUnit
                sh 'mvn test' [cite: 61]
            }
            post {
                success {
                    // Archive les rapports de tests au format XML pour Jenkins
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml' [cite: 64]
                }
            }
        }

        stage('Package code') {
            steps {
                // Génération du fichier final (assurez-vous d'avoir <packaging>war</packaging> dans votre pom.xml)
                sh 'mvn package -DskipTests' [cite: 79]
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Déploiement par copie directe vers le répertoire webapps de Tomcat 10
                // Note : Jenkins doit avoir les droits d'écriture sur ce dossier
                sh 'cp target/*.war /var/lib/tomcat10/webapps/' [cite: 21, 23]
            }
        }
    }
}
