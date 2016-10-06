# project_artifactId
projeto VRaptor4 Web
##init project
Windows

//create copy backend java project
mkdir folderProject
cd folderProject
git clone https://github.com/fidelisfelipe/backend-vraptor-heroku .
import eclipse git project
config JDK e encode(properties>resourc, properties>java build path)

update files with nameProject

\pom.xml > project.artifactId
\src\main\webapp\WEB-INF\web.xml
Web Project Settings > context root > nameProject
src/main/resources/hibernate.cfg.xml  > jdbc3:postgresql://localhost/nameProject
run project tomcat

stop tomcat

heroku create nameProject

git init

heroku git:remote -a nameProject

git remote remove origin
git remote add github git@github.com:fidelisfelipe/nameProject.git

git add .
git commit -m"init project"
git push github master
git push heroku master

heroku restart
heroku open



browser show: <b>{"user":"VRaptor!"}</b>

##init bd
	go: https://postgres.heroku.com/databases
	select you bd
	get params of security and update hibernate.cfg.xml
	create bd: /usuarios/json
	update file - src/main/resources/hibernate.cfg.xml 