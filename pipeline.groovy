pipeline {
    agent any
    stages {
        stage ('SCM checkout'){
            steps{
                retry(3){
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Mr-Nithil/4330-Sheshan.git']])
                }
            }
      
        }
        stage('build docker'){
            steps {
                sh 'docker build -t  nithilsheshan/4330-sheshan .'
            }
        
        }
         stage('run'){
            steps{
                sh 'docker run -d -p 5000:3000 nithilsheshan/4330-sheshan .'
         }
         }
         stage('show running containners'){
      steps{
        sh 'docker ps'
      }
    }
    }
}