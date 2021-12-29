pipeline {

	agent any

	environment {
		dockerHome = tool 'Docker'
		mavenHome  = tool 'Maven'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
	}

	stages {
		
		stage('Checkout') {
			steps {
				checkout scm
				echo "$PATH"
				echo "$env.BUILD_NUMBER"
				echo "$env.BUILD_ID"
				echo "$env.JOB_NAME"
				echo "$env.BUILD_TAG"
				echo "$env.BUILD_URL"
			}
		}

		stage('Build'){
			steps {
				sh "mvn clean compile"
			}
		}

		// stage('Test'){
		// 	steps {
		// 		sh "mvn test"
		// 	}
		// }

		// stage('Integration Test'){
		// 	steps {
		// 		sh "mvn failsafe:integration-test failsafe:verify"
		// 	}
		// }

		stage('Package'){
			steps {
				sh "mvn package -DskipTests"
				archiveArtifacts artifacts: 'target/*', fingerprint: true
			}	
		}

		stage('Sonar') {
			steps {
				sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar -Dsonar.host.url=http://sonarqube:9000"
			}
		}

		stage('Build Docker Image') {
			steps {
				//sh "docker build -t rangakaranam/currency-exchange-azure:$env.BUILD_TAG  --pull --no-cache ."
				script {
					dockerImage = docker.build("rangakaranam/currency-exchange-azure:${env.BUILD_ID}")
				}
			}
			
		}

		stage('Push Docker Image') {
			steps {
				script {
					docker.withRegistry( '', 'dockerhub' ) {
						dockerImage.push()
						dockerImage.push('latest')
					}
				}
			}
			
		}

	}
	post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }

}


