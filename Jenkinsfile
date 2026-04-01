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
                sh 'mvn compile'
            }
        }

        stage('Test code') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/*.xml'
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