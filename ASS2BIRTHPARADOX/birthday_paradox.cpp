//============================================================================
// Name        : birthday_paradox.cpp
// Author      : Elite Four
// Description : Verifies whether birthday paradox is valid or no
//============================================================================

#include<iostream>
#include<stdlib.h>
#include<math.h>
#define no_people 23
using namespace std;

void independent_events();
void exponential_method();
void experimental_proof();

int main()
{
int choice; 
while(1)
{
cout<<"\nSelect Choice\n";
cout<<"1.Probablity by exponential method \n";
cout<<"2.Probablity by independant events method\n";
cout<<"3.Experiment to verify above probablity\n";
cout<<"4.Exit\n";
cout<<"Enter choice \n";
cin>>choice;
if(choice<1 || choice>4)
{
cout<<"Invalid Choice\n";
exit(0);
}
switch(choice)
{
case 1:
exponential_method();
break;

case 2:
cout<<"\nDisplaying probablities of 2 people having birthday on same day "<<endl;

independent_events();
break;

case 3:
experimental_proof();
break;

case 4:
exit(0);
break;
}
}
return 0;
}

/*
   =====================================================================================================
     Function - Computes exponentially the probability for 2 birthdays to be the same
        -----------------
     Mathematical Calulations
     
      1) P(2 same birthdays)= 1 - P(no same birthdays)
      2) Hence, we find the probability of no 2 people having the same birthday
      3) Two people having birthday on same day = only 1 possiblity (ie. 1 day)
         Therefore the probablity of 2 people having birthday on same day is (1/366)
         So probablity of no 2 people having same birthday is (1 - 1/366) = (365/366) 

      4) No of possible pairs can be found using combination C(people,2) = (people)*(people-1)/2
      5) As these are independant events total probablity of 2 people not having same birthday is
	 (365/366)*(365/366)*..... (total no of pairs) = (365/366)^no of pairs

      6) Therefore, probablity of 2 people having same birthday is (1- ((365/366)^no of pairs))		
   ======================================================================================================
*/
void exponential_method()
{
 float prob_pair, prob_not, prob_having;
 int people,pairs;
 
 //There are 366 days available in an year(including leap)   

 cout<<"Enter no of people ";
 cin>>people;  
 pairs=(people)*(people-1)/2;

 prob_pair=(1.00-(1.00/366));
 
 prob_not=pow(prob_pair,pairs);

 prob_having=1.00-prob_not;

 cout<<"\nProbablity of 2 people having birthday on the same day is  "<<prob_having<<endl; 

}

/*
   =====================================================================================================
     Function - Computes the mathematical probability for 2 birthdays to be the same for no. of people
		varying from 1 to 23
        -----------------
     Mathematical Calulations
     
      1) P(2 same birthdays)= 1 - P(no same birthdays)
      2) Hence, we find the probability of no 2 people having the same birthday
      3) This is done using permutation 
      4) Example:
          For 23 people it will be (366/366)*(365/366)*(364/366)*(363/366)*(362/366)......*(343/366)
          This value is 0.492703

          So, P(2 same birthdays)= 1 - 0.492703 = 0.507297 or 50.7%

   ======================================================================================================
*/
void independent_events()
{
   //Let a1 be the event where 2 people donot have birthdays on same day 
   //Let a2 be the event where 2 people have birthdays on same day
   //There are 366 days available in an year(including leap)  
   int i,k;
   float a1=1,a2=1;
   float no_of_days=366;
   cout<<"\tNo of people\tProbablity"<<endl;
   for(k=1;k<=23;k++)
   {
	   a1=1,a2=1;
	   no_of_days=366;
   for(i=0;i<k;i++)
   {
	   a1=a1*(no_of_days/366);
	   no_of_days--;
   }
   a2=1-a1;
   cout<<"\t"<<k<<"\t\t"<<a2<<endl;
   }
	
}

/*
   =====================================================================================================
     Function - Randomly generates birthdates(nos) for 23 people and checks if there are any matching 
		dates.
		1 number corresponds to 1 unique birthdate, as there are 366 unique birthdates available,
		we generate 23 random nos(people) from 0 to 365	  
		This above experiment is repeated 100 times 
        -----------------
     Mathematical Calulations
     		1)Probability for 100 trials where 2 people having same birthday is
		   No of times matching pair found/ Total no of trials
      
   ======================================================================================================
*/

void experimental_proof()
{
 //There are 366 days available in an year(including leap)
 int j,i,a[100],count=0,b_count=0,no_of_trials=0;
 float prob;
do
{
count=0;
for(i=0;i<no_people;i++)
{
j=rand()%366;
a[i]=j;

}

cout<<"Generated dates are "<<endl;
for(i=0;i<no_people;i++)
{
  cout<<a[i]<<"\t";
}
cout<<"\nMatching pairs are : \n";
for(i=0;i<no_people;i++)
{
for(j=0;j<no_people;j++)
{
 if(a[i]==a[j])

 {
  
  if(i<j)
  {
   cout<<"("<<a[i]<<", "<<a[i]<<")"<<"\t";
   count++;
  }
  
 }
}
}
cout<<"\nNo of matching pairs are: "<<count<<endl<<endl;
no_of_trials++;
if(count>0)
b_count++;
}while(no_of_trials<100);
prob=(float)b_count/(float)no_of_trials;
cout<<"Probability is no of times matching pair found/ total no of trials"<<endl;
cout<<"Probability is "<<b_count<<"/"<<no_of_trials<<endl;
cout<<"Probability is "<<prob<<endl;
}
