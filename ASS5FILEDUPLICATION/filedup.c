/*
	=====================================================================================
	TITLE  -  Listing Duplicate Files
	-------------------------------------------------------------------------------------
	AUTHOR -  ELITE FOUR
	-------------------------------------------------------------------------------------
	DATE   -  1st June, 2015
	-------------------------------------------------------------------------------------
	BRIEF DESCRIPTION -
	-------------------
	Place this file in folder where you want to find duplicate files.It lists out all the
        duplicate files and gives you option to delete it.

        The program first lists all files in folder and its subfolder sorted according to size in
        bytes.Files having same size are check for extension and files having same extension are
        compared byte wise to see if there contents are same.


	======================================================================================
*/


//inclusion of header files

#include<stdio.h>
#include<stdlib.h>
#include<string.h>


//structure which stores file name and size for comparison
struct files
{

    char name[150];
    long int size;
} file[2];


//function declaration
void finddup();
void init();
int findexten();
int comparedata();

/*global variables
  prevf is used to store last compared file.
  count gives number of list of duplicate files
*/
char prevf[100],count=1;


//main function

int main()
{

    char c,name[100];


//this system call generates list of all files in given folder,sorts according to its size in bytes and stores in file tem.txt
//making use of this avoids sorting of files.
//Instead of using system call we can also list all files in folder by making use of dirent,get size using size and then sort it
    system("find . -type f -ls | sort -r -n -k7 > temp.txt");


    printf("\nDuplicate Files\n\n");

    init(); //function reads file and find duplicate files
    printf("\n\n");

//giving user option to delete
    printf("\nDo you Want to delete some files?(y/n)");
    scanf("%c",&c);



    if( c=='y' || c=='Y')
    {
        do
        {
            printf("\nEnter file name(with path)you want to delete :: ");
            scanf("%s",name);
            remove(name);
            printf("\nDo you want to delete some more files?(y/n)");
            scanf("%c%c",&c,&c);
        }
        while(c=='y' || c=='Y');
    }



    remove("temp.txt"); //delete the temporary file made

    return 0;
}


//this function first compares size,then extension and then contents of file to find duplicate files

void finddup()
{
    int i,c;

    if(file[0].size==file[1].size) //size of two files is same
    {
        i=findexten();

        if(i==1)  //size and extension of two files are same
        {
            c=comparedata(); //check contents of file  bytewise

            file[0]=file[1]; //switch structures
            return;

        }
        else //extension not same
        {
            file[0]=file[1];
            return;
        }
        file[0]=file[1];
    }
    else //size not same
    {
        file[0]=file[1];
        return;
    }

}


//function initializes struct.two files are taken at a time in struct and they are compared

void init()
{
    FILE *fp;
    char buffer[200],temp[100],temp4[100],temp1[100],temp2[100],temp3[100];
    int n,l;


    fp=fopen("temp.txt","r");

//error handling
    if(fp==NULL)
    {
        printf("ERROR OCCURED!! PROGRAM IS EXITING");
        exit(0);
    }



//get  first line
    fgets(buffer,200,fp);
    n=sscanf(buffer,"%s %s %s %s %s %s %ld %s %s %s %s %s %s %s %s",temp,temp,temp,temp,temp,temp,&file[0].size,temp,temp,temp,file[0].name,temp1,temp2,temp3,temp4);

    if(n>11) //this handling is required if files/subfolder names contains space
    {
        n=n-11;
        if( n==1)
        {
            strcat(file[0].name," ");
            strcat(file[0].name,temp1);
        }
        else if( n==2)
        {
            strcat(file[0].name," ");
            strcat(file[0].name,temp1);
            strcat(file[0].name," ");
            strcat(file[0].name,temp2);
        }
        else  if( n==3)
        {
            strcat(file[0].name," ");
            strcat(file[0].name,temp1);
            strcat(file[0].name," ");
            strcat(file[0].name," ");
            strcat(file[0].name,temp2);
            strcat(file[0].name," "); 
            strcat(file[0].name,temp3);
        }
        else
        {
            strcat(file[0].name," ");
            strcat(file[0].name,temp1);
            strcat(file[0].name," ");
            strcat(file[0].name,temp2);
            strcat(file[0].name," "); 
            strcat(file[0].name,temp3);
            strcat(file[0].name," ");
            strcat(file[0].name,temp4);
        }

    }


//read entire file ine line at a time
    while( fgets(buffer,200,fp))
    {


        //get next line

        n=sscanf(buffer,"%s %s %s %s %s %s %ld %s %s %s %s %s %s %s %s",temp,temp,temp,temp,temp,temp,&file[1].size,temp,temp,temp,file[1].name,temp1,temp2,temp3,temp4);
        if(n>11)
        {
            n=n-11;
            if( n==1)
            {
                l=strlen(file[1].name)-1; //used to remove '\'
                file[1].name[l]='\0';
                strcat(file[1].name," ");   
                strcat(file[1].name,temp1);
            }
            else if( n==2)
            {
 
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';
                strcat(file[1].name," ");
                strcat(file[1].name,temp1);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';
                strcat(file[1].name," ");
                strcat(file[1].name,temp2);
            }
            else  if( n==3)
            {
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';
                strcat(file[1].name," ");
                strcat(file[1].name,temp1);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';
                strcat(file[1].name," ");
                strcat(file[1].name,temp2);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';   
                strcat(file[1].name," ");
                strcat(file[1].name,temp3);
            }
            else
            {
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';  
                strcat(file[1].name," ");
                strcat(file[1].name,temp1);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0'; 
                strcat(file[1].name," ");
                strcat(file[1].name,temp2);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0';
                strcat(file[1].name," ");
                strcat(file[1].name,temp3);
                l=strlen(file[1].name)-1;
                file[1].name[l]='\0'; 
                strcat(file[1].name," ");
                strcat(file[1].name,temp4);
            }

        }

       
        finddup();  //check if two files are same

    }


}

/*function checks if two files have same extension or not.
returns -1 if different extensions and 1 if same extensions
*/
int findexten()
{
    int f1,f2;

    f1=strlen(file[0].name)-1;
    f2=strlen(file[1].name)-1;

    if(file[0].name[f1]==file[1].name[f2]) //compare starting from end each character till '.' is not encountered
    {
        while(file[0].name[f1]!='.' || file[1].name[f2]!='.')
        {
            if(file[0].name[f1]!=file[1].name[f2])
                return -1;
            f1--;
            f2--;
        }
    }


    return 1;
}


//function compares contents of two files byte wise

int comparedata()
{
    const char ERROR_RANGE = 0x1010;
    char buf1,buf2;
    FILE *fp,*fp1;


    fp=fopen(file[0].name,"r");
    fp1=fopen(file[1].name,"r");


    fread(&buf1,1,1,fp);
    fread(&buf2,1,1,fp1);



    if (abs(buf1 - buf2) > ERROR_RANGE) //two files are different
    {
        return -1;
    }

//two files are same

    if( strcmp(prevf,file[0].name)==0) //present file is duplicate of previous file which is duplicate of its predecessor.So display it togethere
    {
        printf("\t\t%s",file[1].name);
        strcpy(prevf,file[1].name);
    }

    else
    {
        printf("\n%d\t%s",count,file[0].name);
        printf("\t\t%s",file[1].name);
        strcpy(prevf,file[1].name);
        count++;
    }
    return 1;
}

