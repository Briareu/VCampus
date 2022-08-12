
# VCampus
developed by Java
开发请在dev分支进行
如需在main下建新的分支请pull request
注意代码的同步更新
开发愉快

Eclipse版本: 2022-06 (4.24.0)

Java版本:
    java version "1.8.0_201"
    Java(TM) SE Runtime Environment (build 1.8.0_201-b09))
	JDK version 1.8.0_341

Java插件：Javafx，Scene Builder(可选)
安装JavaFX后，请在项目中添加JavaFX SDK

git流程：
	•  Git clone -b dev git@github.com:Briareu/VCampus.git
	•  修改至下载下来的文件夹
	•  cd Vcampus
	•  git init
	•  git add .
	•  git commit -m "your name, date, message you want show"
	•  git pull  git@github.com:Briareu/VCampus.git dev
	•  git push -u  git@github.com:Briareu/VCampus.git dev
（出现error: src refspec dev does not match any, 切换一下本地分支：git checkout -b dev）
