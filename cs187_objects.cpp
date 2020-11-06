#include<iostream>
using namespace std;

class Users{
  public:

   Users(){
     name = " ";
     account = " ";
   }

   void setName(){
     cin>>name;
   }

   void setAccount(){
     cin>>account;
   }

   int getName(){return name;}
   int getAccount(){return account;}

  private:
   string name, account;
};


class Profile{
   public:

    Profile(){
      profile_record_1 = 0;
      profile_record_2 = 0;
      profile_record_3 = 0;
    }

    void profile_question_1(){
      cout<<"Profiling question 1: In a scale of 1 to 10, what do you think of sustainability?";
      cin>>profile_record_1;
    }

    void profile_record_2(){
      cout<<"What is your monthly comsumption bill? ";
      cin>>profile_record_2;
    }

    void profile_record_3(){
      cout<<"Your determination in a sustainabile life? ";
      cin>>profile_record_3;
    }

    int getRecord1(){return profile_record_1;}
    int getRecord2(){return profile_record_2;}
    int getRecord3(){return profile_record_3;}




   private:
     int profile_record_1, profile_record_2, profile_record_3;

};



class algorithm{
public: profile

  void result(){
    computed_result = 3 * profile_record_1 + 2.5 * profile_record_2 + 1.7 * profile_record_3
  }

private:
  double computed_result;  

}
