#!/bin/bash

export INITIAL_GIT_TAG=v-1.0

function help() {
	echo "Select from the following options: "
	printf " %s \t %s \n" "1" "Add new feature"
	printf " %s \t %s \n" "2" "Fix the bug"
	printf " %s \t %s \n" "3" "Reset environment"
	printf " %s \t %s \n" "4" "Exit"
	
	echo -e "Please enter your option: \c"
	read OPTION

	case $OPTION in
	1 )
		logInfo "Feature is being added"
		add_feature
		;;
	2 )
		logInfo "Feature fix is being added"
		add_feature_fix
		;;
	3 )
		logInfo "Resetting the branch"		
		reset				
		;;
	4 )
		logSuccess "Have a good day!"			
		exit 0
		;;
	esac	
}

function add_feature() {
	cp -r code_staging/feature/* .
	git add *
	git commit -m "Adding the feature"
	git push origin master
	logSuccess "Feature added and committed"
	
	help
}

function add_feature_fix() {
	cp -r code_staging/fix/* .
	git add *
	git commit -m "Adding the feature fix"
	git push origin master
	logSuccess "Feature fix added and committed"
	
	help
}

function reset() {
	git reset --hard $INITIAL_GIT_TAG
	git commit -m "Resetting post demo"
	git push origin master --force
	logSuccess "Done with branch reset"
	
	help
}

logSuccess () {
	logCustom 2 "$1"	
}

logInfo () {
	logCustom 3 "$1"
}

logCustom () {
	tput setaf $1
	echo "$2"
	tput sgr 0	
}

help				

