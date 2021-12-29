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
        echo "environment path  $PATH" 
    }
    stages{
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
                sh "mvn package"
            }
        }
    }

    post{
        success{
            echo "successfully build !!!!!!"
        }
    }
}
