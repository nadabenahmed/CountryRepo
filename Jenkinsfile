pipeline {
    agent any

    tools {
        maven 'Maven3'
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
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MySonarQubeServer') {
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=country-service \
                    -Dsonar.projectName=country-service \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=YOUR_TOKEN
                    """
                }
            }
        }

        stage('Package code') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                sh 'java -jar target/*.jar &'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
    }
}
