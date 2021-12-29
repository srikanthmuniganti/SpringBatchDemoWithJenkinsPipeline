// Scripted pipeline 
//node{
//	stage('Test'){
//		echo "Test"
//	}
	
//	stage('build'){
//		echo "build"
//	}
//}

// Declarative Pipeline

pipeline {

    agent any
    
    environment{
        dockerHome = tool 'Docker Configured'
        mavenHome = tool 'Maven'
        PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
        //echo "environment -path  ----> $PATH"
    }
    stages{
        stage('Environment variable values'){
            steps{
                echo "PATH - $PATH"
                echo "BUILD_NUMBER - $env.BUILD_NUMBER"
                echo "BUILD_ID - $env.BUILD_ID"
                echo "JOB_NAME - $env.JOB_NAME"
                echo "BUILD_TAG - $env.BUILD_TAG"
            }
        }
        stage('clean stage'){
            steps{
                echo "clean stage"
                sh "mvn clean"
            }
        }
        stage('compile stage'){
            steps{
                echo "compile stage"
                sh "mvn compile"
            }            
        }
        stage('build stage'){
            steps{
                echo "build stage"
                sh "mvn package -DskipTests"
            }
        }
    }

    post{
        success{
            echo "successfully build !!!!!!"
        }
        failure{
            echo "failure happened !!!!!!!"
        }
        always{
            echo "I'm always executed!!!!!!"
        }
        changed{
            echo "build status changed !!!!!"
        }
    }
}


