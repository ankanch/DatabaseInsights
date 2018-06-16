#include <fstream>
#include <iostream>
using namespace std; 
int main (int argc, char** argv) 
{ 
  cout<<argc<<endl;
  if(argc == 3){ 
    fstream is; 
    is.open(argv[1],ios::out); 
    is<<argv[2]; 
    is.close();  
  }else{ 
    cout<<"usage saverep [id] [report data]"<<endl; 
  } 
  return 0; 
}