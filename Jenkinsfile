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
                // On utilise -DskipTests si votre base de données n'est pas prête, 
                // sinon laissez 'mvn test'
                sh 'mvn test -DskipTests'
            }
            post {
                success {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'MySonarQubeServer') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=country-service -Dsonar.projectName=country-service"
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
                // Copie du fichier WAR vers le répertoire de déploiement Tomcat
                sh 'cp target/*.war /var/lib/tomcat10/webapps/'
            }
        }
    }

    post {
        success {
            echo '--------------------------------------------------'
            echo 'Pipeline completed successfully!'
            echo '--------------------------------------------------'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
