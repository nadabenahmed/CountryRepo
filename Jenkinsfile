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
        // On utilise 'sh' pour copier le fichier vers le dossier de Tomcat
        // Adaptez le chemin '/opt/tomcat/webapps' selon votre installation
        sh 'cp target/*.jar /chemin/vers/votre/tomcat/webapps/'
        
        // Ou si vous utilisez le plugin "Deploy to container", utilisez cette syntaxe :
        /*
        deploy adapters: [tomcat9(credentialsId: 'tomcat-admin', url: 'http://localhost:8080')], 
               contextPath: 'mon-application', 
               war: 'target/*.jar'
        */
         }
         }
        }
    }
}
