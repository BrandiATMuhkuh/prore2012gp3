/*
  Receives Test Events from your phone.
 After it gets a test message the led 13 will blink
 for one second.
 */

#include <MeetAndroid.h>

MeetAndroid meetAndroid;
int startButton = 2;
int startButtonState = 0;
int ledCount = 7;
int allLEDS = LOW;

//gruppen vortschritt
int g_1 = 3;
int g_2 = 4;
int g_3 = 5;
int g_4 = 6;
int g_5 = 7;
int g_6 = 8;
int g_7 = 9;

//einzel vortschritt
int e_1 = A4;
int e_2 = A3;
int e_3 = 12;
int e_4 = 13;
int e_5 = A0;
int e_6 = A1;
int e_7 = A2;


//notify
int g_n = 11;
int e_n = 10;
int g_nOn = false;
int e_nOn = false;
int e_nVal = 0;
int g_nVal = 0;
int e_nSwUp = true;
int g_nSwUp = true;

//fading
int fadeValue = 0;
boolean fadeUp = true;

//testrun
int testrun = 1;

void setup()  
{
  // use the baud rate your bluetooth module is configured to 
  // not all baud rates are working well, i.e. ATMEGA168 works best with 57600
  //115000 firefly
  //57600 old
  Serial.begin(57600); 

  // register callback functions, which will be called when an associated event occurs.
  // - the first parameter is the name of your function (see below)
  // - match the second parameter ('A', 'B', 'a', etc...) with the flag on your Android application
  meetAndroid.registerFunction(testEvent, 'A');  
  meetAndroid.registerFunction(setProgresses, 'B');   
  meetAndroid.registerFunction(setGroupNotificatinoLight, 'D');  
  meetAndroid.registerFunction(setMyNotificatinoLight, 'E');  

  pinMode(startButton, INPUT);

  //set modes
  pinMode( g_1, OUTPUT);
  pinMode( g_2, OUTPUT);
  pinMode( g_3, OUTPUT);
  pinMode( g_4, OUTPUT);
  pinMode( g_5, OUTPUT);
  pinMode( g_6, OUTPUT);
  pinMode( g_7, OUTPUT);
  
  //einzel vortschritt
  pinMode( e_1, OUTPUT);
  pinMode( e_2, OUTPUT);
  pinMode( e_3, OUTPUT);
  pinMode( e_4, OUTPUT);
  pinMode( e_5, OUTPUT);
  pinMode( e_6, OUTPUT);
  pinMode( e_7, OUTPUT);
  
  
  //notify
  pinMode( g_n, OUTPUT);
  pinMode( e_n, OUTPUT);
  
  //-------------------------
  //set init states
  setAllLeds(HIGH);
}

void loop()
{
  meetAndroid.receive(); // you need to keep this in your loop() to receive events

  startButtonState = digitalRead(startButton);
  
  
  
  //Runs as long as button is pressed
  while (startButtonState == HIGH){
    int button_delay=0;
    
    //count button press time
    
    while (startButtonState == HIGH){
      button_delay++;
      delay(100);
      startButtonState = digitalRead(startButton);
      
      //Long press button
      if(button_delay == 10){ //no need to wait for user to release
        sendStart(); 
      }        
      
    }
    
    //short press button
    if(button_delay < 10) { //short press
      sendShowProgress();
    }
    

    
  }
  
  if(testrun==1){
    nightRider();
    setAllLeds(HIGH);
    delay(1000);
    setAllLeds(LOW);
    testrun = 0;
  }
  
  //pwm
  //fadeUp
  //fadeValue
  
  if(fadeUp == true){
    // fade in from min to max in increments of 5 points: 
    // sets the value (range from 0 to 255):
    if(g_nOn == true){
      analogWrite(g_n, fadeValue);
    }
    
    if(e_nOn == true){
      analogWrite(e_n, fadeValue);
    }
    // wait for 30 milliseconds to see the dimming effect    
    fadeValue +=5;
    
    if(fadeValue>=255){
      fadeUp = false;
    }
    
    delay(30);       
  }
  
  if(fadeUp == false){
    // fade in from min to max in increments of 5 points: 
    // sets the value (range from 0 to 255):
    if(g_nOn == true){
      analogWrite(g_n, fadeValue);
    }
    
    if(e_nOn == true){
      analogWrite(e_n, fadeValue);
    }
    // wait for 30 milliseconds to see the dimming effect    
    fadeValue -=5;
    
    if(fadeValue<=0){
      fadeUp = true;
    }
    
    delay(30);  
  }
  
  //test pwm
  //pwmTest();
}

void pwmTest(){
  //g_7 = pin 9 kann PWM
  //e_1 = pin 10 kann PWM
  //e_2 = pin 11 kann PWM
  
  // fade in from min to max in increments of 5 points:
  for(int fadeValue = 0 ; fadeValue <= 255; fadeValue +=5) { 
    // sets the value (range from 0 to 255):
    if(g_nOn == true){
      analogWrite(g_n, fadeValue);
    }
    
    if(e_nOn == true){
      analogWrite(e_n, fadeValue);
    }
    // wait for 30 milliseconds to see the dimming effect    
    delay(30);                            
  } 

  // fade out from max to min in increments of 5 points:
  for(int fadeValue = 255 ; fadeValue >= 0; fadeValue -=5) { 
    // sets the value (range from 0 to 255):
    if(g_nOn == true){
      analogWrite(g_n, fadeValue);
    }
    
    if(e_nOn == true){
      analogWrite(e_n, fadeValue);
    }     
    // wait for 30 milliseconds to see the dimming effect    
    delay(30);                            
  } 
}

void setAllProgLeds(int onOff){
   //-------------------------
  //set init states
  digitalWrite( g_1, onOff);
  digitalWrite( g_2, onOff);
  digitalWrite( g_3, onOff);
  digitalWrite( g_4, onOff);
  digitalWrite( g_5, onOff);
  digitalWrite( g_6, onOff);
  digitalWrite( g_7, onOff);
  
  //einzel vortschritt
  digitalWrite( e_1, onOff);
  digitalWrite( e_2, onOff);
  digitalWrite( e_3, onOff);
  digitalWrite( e_4, onOff);
  digitalWrite( e_5, onOff);
  digitalWrite( e_6, onOff);
  digitalWrite( e_7, onOff);
  
}

void setAllLeds(int onOff){
   
  setAllProgLeds(onOff);
  
  //notify
  digitalWrite( g_n, onOff);
  digitalWrite( e_n, onOff);
}

void nightRider(){
  
  int mydelay = 25;
  
  
  digitalWrite( g_1, HIGH);
  delay(mydelay);
  digitalWrite( g_1, LOW);
  digitalWrite( g_2, HIGH);
  delay(mydelay);
  digitalWrite( g_2, LOW);
  digitalWrite( g_3, HIGH);
  delay(mydelay);
  digitalWrite( g_3, LOW);
  digitalWrite( g_4, HIGH);
  delay(mydelay);
  digitalWrite( g_4, LOW);
  digitalWrite( g_5, HIGH);
  delay(mydelay);
  digitalWrite( g_5, LOW);
  digitalWrite( g_6, HIGH);
  delay(mydelay);
  digitalWrite( g_6, LOW);
  digitalWrite( g_7, HIGH);
  delay(mydelay);
  digitalWrite( g_7, LOW);
  digitalWrite( g_7, HIGH);
  delay(mydelay);
  digitalWrite( g_7, LOW);
  digitalWrite( g_6, HIGH);
  delay(mydelay);
  digitalWrite( g_6, LOW);
  digitalWrite( g_5, HIGH);
  delay(mydelay);
  digitalWrite( g_5, LOW);
  digitalWrite( g_4, HIGH);
  delay(mydelay);
  digitalWrite( g_4, LOW);
  digitalWrite( g_3, HIGH);
  delay(mydelay);
  digitalWrite( g_3, LOW);
  digitalWrite( g_2, HIGH);
  delay(mydelay);
  digitalWrite( g_2, LOW);
  digitalWrite( g_1, HIGH);
  delay(mydelay);
  digitalWrite( g_1, LOW);
  
  
  digitalWrite( e_1, HIGH);
  delay(mydelay);
  digitalWrite( e_1, LOW);
  digitalWrite( e_2, HIGH);
  delay(mydelay);
  digitalWrite( e_2, LOW);
  digitalWrite( e_3, HIGH);
  delay(mydelay);
  digitalWrite( e_3, LOW);
  digitalWrite( e_4, HIGH);
  delay(mydelay);
  digitalWrite( e_4, LOW);
  digitalWrite( e_5, HIGH);
  delay(mydelay);
  digitalWrite( e_5, LOW);
  digitalWrite( e_6, HIGH);
  delay(mydelay);
  digitalWrite( e_6, LOW);
  digitalWrite( e_7, HIGH);
  delay(mydelay);
  digitalWrite( e_7, LOW);
  digitalWrite( e_7, HIGH);
  delay(mydelay);
  digitalWrite( e_7, LOW);
  digitalWrite( e_6, HIGH);
  delay(mydelay);
  digitalWrite( e_6, LOW);
  digitalWrite( e_5, HIGH);
  delay(mydelay);
  digitalWrite( e_5, LOW);
  digitalWrite( e_4, HIGH);
  delay(mydelay);
  digitalWrite( e_4, LOW);
  digitalWrite( e_3, HIGH);
  delay(mydelay);
  digitalWrite( e_3, LOW);
  digitalWrite( e_2, HIGH);
  delay(mydelay);
  digitalWrite( e_2, LOW);
  digitalWrite( e_1, HIGH);
  delay(mydelay);
  digitalWrite( e_1, LOW);
  
  //blue
  delay(mydelay);
  digitalWrite( g_n, LOW);
  delay(mydelay);
  digitalWrite( g_n, HIGH);
  delay(mydelay);
  digitalWrite( e_n, LOW);
  delay(mydelay);
  digitalWrite( e_n, HIGH);

}

/**
* progress must be an int array of TWO values
* value one is my own progress
* value two is the group progress
*/
void setProgresses(byte flag, byte numOfValues){
  int data[numOfValues];
  meetAndroid.getIntValues(data);
  
  meetAndroid.send(data[0]);
  meetAndroid.send(data[1]);
  
  
  setAllProgLeds(LOW);
  setMyGroupProgress(data[0], data[1]);
  //setMyProgress(data[0]);
  //setGroupProgress(data[1]);
  
  delay(4000);
  setAllProgLeds(LOW);
}

void setMyGroupProgress(int myProgress, int grProgress){
  //meetAndroid.send("setGroupProgress. "+progress);
  
  //calculate and set group progress in percent
  int glowingLEDs = round((ledCount * grProgress)/100);
  int myglowingLEDs = round((ledCount * myProgress)/100);
  
  if(glowingLEDs>=1){
    digitalWrite( g_1, HIGH);
  }
  
  if(myglowingLEDs>=1){
    digitalWrite( e_7, HIGH);
  }
  
  delay(200);
  
  if(glowingLEDs>=2){
    digitalWrite( g_2, HIGH);
  }
  
  if(myglowingLEDs>=2){
    digitalWrite( e_6, HIGH);
  }
  
  delay(200);
  
  if(glowingLEDs>=3){
    digitalWrite( g_3, HIGH);
  }
  
  if(myglowingLEDs>=3){
    digitalWrite( e_5, HIGH);
  }
  delay(200);
  
  if(glowingLEDs>=4){
    digitalWrite( g_4, HIGH);
  }
  
  if(myglowingLEDs>=4){
    digitalWrite( e_4, HIGH);
  }
  delay(200);
  
  if(glowingLEDs>=5){
    digitalWrite( g_5, HIGH);
  }
  
  if(myglowingLEDs>=5){
    digitalWrite( e_3, HIGH);
  }
  delay(200);
  
  
  if(glowingLEDs>=6){
    digitalWrite( g_6, HIGH);
  }
  
  if(myglowingLEDs>=6){
    digitalWrite( e_2, HIGH);
  }
  delay(200);
  
  
  if(glowingLEDs>=7){
    digitalWrite( g_7, HIGH);
  }  
  
  if(myglowingLEDs>=7){
    digitalWrite( e_1, HIGH);
  }
}

void setGroupProgress(int progress){
  //meetAndroid.send("setGroupProgress. "+progress);
  
  //calculate and set group progress in percent
  int glowingLEDs = round((ledCount * progress)/100);
  
  if(glowingLEDs>=1){
    digitalWrite( g_1, HIGH);
  }
  
  if(glowingLEDs>=2){
    digitalWrite( g_2, HIGH);
  }
  
  if(glowingLEDs>=3){
    digitalWrite( g_3, HIGH);
  }
  
  if(glowingLEDs>=4){
    digitalWrite( g_4, HIGH);
  }
  
  if(glowingLEDs>=5){
    digitalWrite( g_5, HIGH);
  }
  
  if(glowingLEDs>=6){
    digitalWrite( g_6, HIGH);
  }
  
  if(glowingLEDs>=7){
    digitalWrite( g_7, HIGH);
  }
}

void setMyProgress(int progress){
  //meetAndroid.send("setMyProgress: "+progress);
  
   //calculate and set group progress in percent
  int glowingLEDs = round((ledCount * progress)/100);
  
  if(glowingLEDs>=1){
    digitalWrite( e_7, HIGH);
  }
  
  if(glowingLEDs>=2){
    digitalWrite( e_6, HIGH);
  }
  
  if(glowingLEDs>=3){
    digitalWrite( e_5, HIGH);
  }
  
  if(glowingLEDs>=4){
    digitalWrite( e_4, HIGH);
  }
  
  if(glowingLEDs>=5){
    digitalWrite( e_3, HIGH);
  }
  
  if(glowingLEDs>=6){
    digitalWrite( e_2, HIGH);
  }
  
  if(glowingLEDs>=7){
    digitalWrite( e_1, HIGH);
  }
}
void setGroupNotificatinoLight(byte flag, byte numOfValues){
  meetAndroid.send("setGroupNotificatinoLight");
  meetAndroid.send(meetAndroid.getInt());
  
  if(meetAndroid.getInt()==0){
    g_nOn = false;
    //g_nVal = 0;
    //g_nSwUp = true;
    digitalWrite( g_n, LOW);
  }
  
  if(meetAndroid.getInt()==1){
    g_nOn = true;
    //digitalWrite( g_n, HIGH);
  }
}
void setMyNotificatinoLight(byte flag, byte numOfValues){
  meetAndroid.send("setMyNotificatinoLight");
  meetAndroid.send(meetAndroid.getInt());
  
  if(meetAndroid.getInt()==0){
    e_nOn = false;
    //e_nVal = 0;
    //e_nSwUp = true;
    digitalWrite( e_n, LOW);
  }
  
  if(meetAndroid.getInt()==1){
    e_nOn = true;
    //digitalWrite( e_n, HIGH);
  }
}

void sendStart(){
  meetAndroid.send("sendstart");
}

void sendShowProgress(){
  meetAndroid.send("sendshowprogress");
}




/*
 * This method is called constantly.
 * note: flag is in this case 'A' and numOfValues is 0 (since test event doesn't send any data)
 */
void testEvent(byte flag, byte numOfValues)
{
  sendStart();

 setAllLeds(allLEDS);
allLEDS= !allLEDS;
}






