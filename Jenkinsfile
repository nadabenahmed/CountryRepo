pipeline {
    agent any

    tools {
        // Doit correspondre au nom configuré dans "Global Tool Configuration" sur Jenkins
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
                // On ajoute -DskipTests pour éviter l'erreur de connexion MySQL (Connection refused)
                sh 'mvn test -DskipTests'
            }
            post {
                success {
                    // Archive les rapports s'ils existent
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package code') {
            steps {
                // Génère le fichier .war ou .jar final
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Étape de déploiement (Livraison continue) demandée dans votre support
                deploy adapters: [tomcat9(path: '', url: 'http://localhost:8080')], 
                       contextPath: 'country-service', 
                       war: 'target/*.war'
            }
        }
    }
}
