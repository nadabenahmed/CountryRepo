pipeline {
    agent any

    tools {
        // Doit correspondre au nom configuré dans "Global Tool Configuration"
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
                // Note : -DskipTests compile les tests mais ne les exécute pas
                sh 'mvn test -DskipTests'
            }
            post {
                always {
                    // Archive les rapports même si les tests échouent
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package code') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Option A : Copie directe (si Tomcat est sur la même machine que Jenkins)
                // Remplacez '/opt/tomcat/webapps/' par votre vrai chemin
                sh 'cp target/*.jar /opt/tomcat/webapps/'
                
                // Option B : Via Plugin "Deploy to container" (Syntaxe correcte)
                /*
                deploy adapters: [tomcat9(credentialsId: 'tomcat-admin', url: 'http://localhost:8080')], 
                       contextPath: 'country-service', 
                       war: 'target/*.jar'
                */
            }
        }
    }
}
