pipeline {
    agent any

    tools {
        maven 'M2_HOME' 
    }

    stages {
        stage('Checkout code') {
            steps {
                git branch: 'master', url: 'https://github.com/nadabenahmed/CountryRepo.git'
            }
        }

        stage('Compile code') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test code') {
            steps {
                // On ignore les tests ici car MySQL n'est pas installé
                sh 'mvn test -DskipTests'
            }
        }

        stage('Package code') {
            steps {
                // Génère le fichier .war pour Tomcat
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Déploiement vers le dossier que vous avez configuré
                sh 'cp target/*.war /var/lib/tomcat10/webapps/'
            }
        }
    }

    // Bloc ajouté pour afficher le message de succès
    post {
        success {
            
            echo 'Pipeline completed successfully!'
            
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
