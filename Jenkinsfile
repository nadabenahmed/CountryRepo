pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    } 

    stages {
        stage('Checkout code') { 
            steps {
                git branch: 'master', url: 'https://github.com/Jmouna/CountryRepo.git'
            }
        }

        stage('Compile code') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test code') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    // Correction du chemin (pas d'espaces dans les caractères joker)
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'MySonarQubeServer', credentialsId: 'sonarqubePWD') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=country-service -Dsonar.projectName=country-service"
                }
            }
        }

        stage('Package code') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
