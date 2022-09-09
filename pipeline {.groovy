pipeline {
    agent any
    stages{
        stage("Clone_git_repo") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/arnabd24/Docker-jenkins.git']]])
            }
        }
        stage('Docker Build') {
            steps{
                sh 'docker build -t php:latest .'
            }
        }
        stage('tag the image'){
            steps {
                sh 'docker tag php:latest arnabd24/php:latest'
                sh 'docker push arnabd24/php:latest'
            }
        }
        stage("Create Container"){
        steps{
            sh 'docker run -d -p 8888:8080 arnabd24/php:latest'
        }
    }
}
}