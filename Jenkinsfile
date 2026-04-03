pipeline {
    agent any
    tools { maven 'M2_HOME' }

    stages {
        stage('Checkout code') {
            steps { git branch: 'master', url: 'https://github.com/nadabenahmed/CountryRepo.git' }
        }
        stage('Compile code') {
            steps { sh 'mvn clean compile' }
        }
        stage('Test code') {
            steps { sh 'mvn test -DskipTests' }
            post {
                success { junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml' }
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
            steps { sh 'mvn package -DskipTests' }
        }
        stage('Deploy to Tomcat') {
            steps { sh 'cp target/*.war /var/lib/tomcat10/webapps/' }
        }
    }
    post {
        success {
            echo '--------------------------------------------------'
            echo 'Pipeline completed successfully!'
            echo '--------------------------------------------------'
        }
    }
}
