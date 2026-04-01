pipeline {
    agent any

    tools {
        // Doit correspondre exactement au nom dans Jenkins > Administrer Jenkins > Tools
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
                sh 'mvn test'
            }
            post {
                success {
                    // Archive les rapports de tests JUnit pour l'affichage dans Jenkins
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package code') {
            steps {
                // Génère le fichier .war (nécessite <packaging>war</packaging> dans le pom.xml)
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Copie le fichier vers le dossier webapps de Tomcat 10
                sh 'cp target/*.war /var/lib/tomcat10/webapps/'
            }
        }
    }
}
