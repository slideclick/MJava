    
    
    
        // members that identify both customer and teller, as
        // well as maintain information on time of the event,
        // the event type, the length of service required by
        // the customer, and the amount of time customer is
        // forced to wait for service
        int time;
        EventType etype;
        int customerID;     // customers numbered 1, 2, 3,...
        int tellerID;       // tellers numbered 1, 2, 3,...
        int waittime;
        int servicetime;
    public:
        // constructors
        Event(void);
        Event(int t,EventType et,int cn,int tn,
              int wt,int st);
        
        // methods to retrieve private data
        int GetTime(void) const;
        EventType GetEventType(void) const;
        int GetCustomerID(void) const;
        int GetTellerID(void) const;
        int GetWaitTime(void) const;
        int GetServiceTime(void) const;
};

// default constructor - data filled by assignment later
Event::Event(void)
{}

// constructor that initializes all data members of the event
Event::Event(int t,EventType et,int cn,int tn,
             int wt,int st): 
               time(t),etype(et),customerID(cn),tellerID(tn),
               waittime(wt),servicetime(st) 
{}
    
// return the time the event occurs
int Event::GetTime(void) const
{
    return time;
}
    
// return the type of event (arrival, departure)
EventType Event::GetEventType(void) const
{
    return etype;
}
    
// return the customer number
int Event::GetCustomerID(void) const
{
    return customerID;
}
    
// return the teller number
int Event::GetTellerID(void) const
{
    return tellerID;
}
    
// return the time the customer waits for service
int Event::GetWaitTime(void) const
{
    return waittime;
}

// return the amount of teller time needed by customer
int Event::GetServiceTime(void) const
{
    return servicetime;
}

// compare two Event objects using the time at 
// which the events occur. needed for the priority queue
int operator< (Event e1, Event e2)
{
    return e1.GetTime() < e2.GetTime();
}
