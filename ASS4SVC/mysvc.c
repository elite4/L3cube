/* 
	=====================================================================================
	TITLE  -  SIMPLE VERSION CONTROL 
	-------------------------------------------------------------------------------------
	AUTHOR -  ELITE FOUR
	-------------------------------------------------------------------------------------
	DATE   -  1st June, 2015
	-------------------------------------------------------------------------------------
	BRIEF DESCRIPTION - 
	-------------------
	Simple Version control is an easy to use version control program which keeps track 
	of all the previous versions of a text file.File with latest version is opened for 
        performing operations(appending/deleting) and on commiting creates a new version(new file).
        Log file keeps track of each file and its version created.

	
	======================================================================================
*/






//header files 

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*Global Variables
ind is used as an index to keep count of number of files
flag variable is used to see if file is newly created or existing file is opened
*/

int ind,flag;


//structure to save file and its latest version
struct file
{
    char fname[30];
    int version;
} fl[30];


//Function Definition

char * openf(char []);
char * get_fvname(char [],int version);
int getposition(char []);
void append(char [],char []);
void initstruct();
void commit(char []);
void undo();
void delete(char [],char []);
void display();
void createnew();


/*Main function
Task :: It Provides user with different option and takes file name for performing operations
*/

int main()
{


    char fname[30],*name;
    int op,op1;


    printf("\n________________________________________________________________________________");
    printf("\n          SIMPLE VERSION CONTROL");
    printf("\n________________________________________________________________________________\n");

    initstruct();

    do
    {
        printf("\n\n\n________________________________________________________________________________");
        printf("\n1.Display List Of Existing Files\n2.Create A New File\n3.Exit\n\n-----Enter Number For Your Option-----\n\n");
        printf("\n________________________________________________________________________________\n");
        scanf("%d",&op1);

        switch(op1)
        {

        case 1: //displays list of existing file with their latest version
            display();
            break;
        case 2://allows user to create new file
            createnew();
            break;
        case 3: //to exit from program
            printf("\nProgram is exiting....\n\n");
            exit(0);
            break;
        default:
            printf("Wrong Option\n");
        }

    }
    while(1);


    printf("\nEnter File Name which you want to open/create a new file  :: ");
    scanf("%s",fname);

    name=openf(fname); //opens the required file

    //printf("\n%s\n",name);
    printf("\nYour options\n1.Append\n2.Delete\n\n");
    scanf("%d",&op);

    switch(op)
    {
    case 1:  //appending
        append(name,fname);
        break;
    case 2:  //deleting
        delete(name,fname);
        break;
    default:
        printf("\nChoose correct options!!");

    }
    return 0;
}



//function which opens an existing file with latest version or creates a new file if the file does not exits

char * openf(char fname[])
{

    FILE *fp1,*fp2;
    char buffer[50];
    int i,version;

    i=getposition(fname);  //gives the position of file in structure created


    if(i==-1) //file is not present ie a new file is to be created
    {



        fp1=fopen(fname,"w");

        strcpy(fl[ind].fname,fname); //add the file to the structure
        fl[ind].version=0;
        ind ++;

        //error handling
        if ( fp1==NULL)
        {
            printf("\nFile Cannot Be opened!!Some Error Occurred!!!");
            exit(0);
        }

        printf("\nA new file is created!!");

        fp2=fopen("log.txt","a"); //create a metadata file which stores info about files and their versions
        if(fp2==NULL)
        {
            fp2=fopen("log.txt","w");
        }

        fprintf(fp2,"%s %s %d",fname,fname,0);
        fprintf(fp2,"\n");
        fclose(fp2);
        flag=1; //flag set to 1 denotes that a new file is created

    }
    else  //opening an existing file
    {
        version=fl[i].version; //geting the latest version

        if(version!=0)  //file is already present and has 1 version
        {
            fname=get_fvname(fname,version);  //fname now contains name of latest version file

            //printf("\n%s",fname);
            fp1=fopen(fname,"r");

            //error handling
            if ( fp1==NULL)
            {
                printf("\nFile Cannot Be opened!!Some Error Occurred!!!");
                exit(0);
            }
        }

        else
        {
            fp1=fopen(fname,"r"); //open existing file which has version 0

        }



    }

//Reading opened file and outputing it to screen
    i=1;
    printf("\n_________%s________\n\n",fname);
    while( fgets(buffer,50,fp1))
    {
        printf("\n%d\t%s",i,buffer);
        i++;
    }
    printf("\n___________________\n");
    fclose(fp1); //close opened file

    return fname;
}



//function which gets the file name with latest version
char * get_fvname(char *fname,int version)
{
    char *name,*temp;
    int i;

    temp=(char*)calloc(5,sizeof(char));
    name=(char*)calloc(20,sizeof(char));

    for(i=0; fname[i]!='.'; i++)
    {
        name[i]=fname[i];
    }
    name[i]='\0';


    strcat(name,"_"); // make name_
    sprintf(temp,"%d",version);
    strcat(name,temp);
    strcat(name,".txt");

    return name;

}

//function which initializes struct
//It reads the metadata file and initilizes structure when program begins

void initstruct()
{
    FILE *fp;
    char buffer[50],temp[20];
    int j;
    ind=0;
    fp=fopen("log.txt","r");
    if( fp!=NULL)
    {
        fgets(buffer,50,fp);
        while( fgets(buffer,50,fp) )
        {
            sscanf(buffer,"%s%s",temp,temp); //read file.We need second word in a line

            // printf("\n%s",temp);
            if( ind!=0 ) //struct is not empty
            {
                for(j=0; j<ind; j++)
                {
                    if( strcmp(fl[j].fname,temp)==0) //compare with name already stored.
                    {
                        (fl[j].version) ++; //name is already present.So increment its version
                        break;
                    }
                }
                if(j==ind)
                {
                    strcpy(fl[ind].fname,temp); //file is not present structure.So add it
                    fl[ind].version=0;
                    ind++;
                }


            }
            else
            {
                strcpy(fl[0].fname,temp);  //struct is empty.so directly save name in first field
                fl[0].version=0;
                ind++;
            }

        }


    }
}


//function which gives index where a file is in structure

int getposition(char fname[])
{
    int i;

    for(i=0; i<ind; i++)
    {
        if( strcmp(fname,fl[i].fname) == 0)
        {
            return i;
        }
    }
    return -1;
}

//function appends 1 line at atime to the opened file

void append(char fname[],char oname[])
{

    FILE *fp1,*fp2;
    char buffer[50];
    int op;

    fp1=fopen(fname,"r");
    fp2=fopen("tem.txt","w"); //create a temporary file.Make changes in it

    while(fgets(buffer,50,fp1))
    {
        fputs(buffer,fp2);

    }
    fclose(fp2);
    fclose(fp1);

    fp2=fopen("tem.txt","a+");

    printf("\nEnter Line to be appended :: ");
    scanf("%s",buffer);
    fputs(buffer,fp2);
    printf("\nFile is succesfully appended\n\nYour options::\n1.Commit\n2.Undo changes");
    scanf("%d",&op);

    if(op==1)
        commit(oname);   //if commited then temorary file is renamed to next version
    else if (op==2)
        undo();         //if undo then temorary file is deleted
    else
        printf("\nWrong option!!");
    fclose(fp2);
}

//Undo deletes the temorary file created with changes as user wishes not to commit
void undo()
{

    remove("tem.txt");
    printf("\nChanges Are Not  Saved!!!");
}


//commit creates a file with new version
void commit(char fname[])
{
    FILE *fp1;
    int i;
    char *t,c[2];

    i=getposition(fname);
    if(flag==1) //created file is new.So its version is 0
    {
        remove(fname);
        rename("tem.txt",fname);
        flag=0;
    }
    else
    {
        fl[i].version ++; //increment the latest version
        t=get_fvname(fname,fl[i].version); //get name for new file

        fp1=fopen("log.txt","a+");  //make changes in metadatafile

        fprintf(fp1,"%s %s %d",t,fname,fl[i].version);

        fprintf(fp1,"\n");
        fclose(fp1);

        rename("tem.txt",t);  //rename the temorary file created to the name for latest version
        printf("\nVersion :: %d Succesfully created!!\n",fl[i].version);
    }
}


//function deletes one line which user wishs

void delete(char fname[],char oname[])
{
    int n,i,op,count=0;
    FILE *fp1,*fp2;
    char buffer[50];

    fp1=fopen(fname,"r");
    fp2=fopen("tem.txt","w"); //creates temporary file

    while(fgets(buffer,50,fp1))
    {
        count++; //checks no of lines originally present.User should enter line within this range
    }
    rewind(fp1);

    //validation for appropriate line no
    do
    {
        printf("\nEnter Line number to be deleted :: ");
        scanf("%d",&n);
        if(n<=0 || n>count)
            printf("\nEnter Valid Line Number!!!\n");
    }
    while(n<=0 || n>count);


    //performing deletion
    i=1;

    while(i<n)
    {

        fgets(buffer,50,fp1);
        fputs(buffer,fp2);
        i++;
    }

    fgets(buffer,50,fp1);

    while(fgets(buffer,50,fp1))
    {
        fputs(buffer,fp2);
    }

    printf("\nLine is succesfully deleted\n\nYour options::\n1.Commit\n2.Undo changes");
    scanf("%d",&op);


    if(op==1)
        commit(oname);   //commit saves the changes
    else if (op==2)
        undo();         //undo ignores the changes
    else
        printf("\nWrong option!!");
    fclose(fp2);
    fclose(fp1);

}


//function displays all the files present
void display()
{

    int i;
    char fname[30],*name;
    int op;

    for(i=0; i<ind; i++)
    {
        printf("\n%d. File Name :: %s\t\tLatest Version :: %d\n",i+1,fl[i].fname,fl[i].version);
    }

    printf("\nEnter File Name which you want to open for appending/deleting :: ");
    scanf("%s",fname);

    name=openf(fname);
    //printf("\n%s\n",name);
    printf("\nYour options\n1.Append\n2.Delete\n\n");
    scanf("%d",&op);

    switch(op)
    {
    case 1:  //appending
        append(name,fname);
        break;
    case 2:  //deleting
        delete(name,fname);
        break;
    default:
        printf("\nChoose correct options!!");

    }
}

//function allows user to create a new file
void createnew()
{

    char fname[30],*name;



    printf("\nEnter File Name which you want to create :: ");
    scanf("%s",fname);

    name=openf(fname);
    // printf("\n%s\n",name);
    printf("\nEnter Text for Appending\n\n");
    //appending ie enter some text to the newly created file
    append(name,fname);


}
