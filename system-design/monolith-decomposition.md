# Monolith Decomposition

## AWS Assignment

Your deliverable will be the base for further, in-depth technical discussion.

Look at the architecture in the diagram:

- List and explain the downsides of the architecture
- Create an alternative, scalable architecture diagram

Notes:

- Web applications and backend APIs use the same version for all EC2 instances
- For more tenants, another monolith instance is spun up and the associated users connect to it with a new API URL.

Additional concerns:

- What about the database backups and security?


```mermaid
flowchart BT
    subgraph SHARED["Shared Services"]
        direction LR
        EMAIL["Email Service<br/>(Spring Boot)<br/>EC2 Instance"]
        PUSH["Push Notifications Service<br/>(Spring Boot)<br/>EC2 Instance"]
        FILES["File Storage Service<br/>(Spring Boot)<br/>EC2 Instance (500 GiB)"]
        EMAIL ~~~ PUSH ~~~ FILES  %% (force layout)
    end

    %% ---------- Tenant 1 ----------
    subgraph T1["EC2 Instance 1"]
        direction LR
        W1["Web Application<br/>(Angular.js)"]
        A1["Backend API<br/>(Spring Boot)"]
        D1[("Database<br/>(postgresql)")]
        W1 ~~~ A1 ~~~ D1  %% (force layout)
    end

    %% ---------- Tenant 2 ----------
    subgraph T2["EC2 Instance 2"]
        direction LR
        W2["Web Application<br/>(Angular.js)"]
        A2["Backend API<br/>(Spring Boot)"]
        D2[("Database<br/>(postgresql)")]
        W2 ~~~ A2 ~~~ D2  %% (force layout)
    end

    %% ---------- Tenant X ----------
    subgraph TX["EC2 Instance X"]
        direction LR
        WX["Web Application<br/>(Angular.js)"]
        AX["Backend API<br/>(Spring Boot)"]
        DX[("Database<br/>(postgresql)")]
        WX ~~~ AX ~~~ DX  %% (force layout)
    end

    %% ---------- Clients ----------
    subgraph CL1[" "]
        direction LR
        C1A["📱 Client 1"]
        C1B["💻 Client 2"]
        C1C["🖥️ Client X"]
        C1A ~~~ C1B ~~~ C1C  %% (force layout)
    end

    subgraph CL2[" "]
        direction LR
        C2A["📱 Client 1"]
        C2B["💻 Client 2"]
        C2C["🖥️ Client X"]
        C2A ~~~ C2B ~~~ C2C  %% (force layout)
    end

    subgraph CLX[" "]
        direction LR
        CXA["📱 Client 1"]
        CXB["💻 Client 2"]
        CXC["🖥️ Client X"]
        CXA ~~~ CXB ~~~ CXC  %% (force layout)
    end

    %% ---------- Client traffic ----------
    CL1 -->|http://api-1.company.com| T1
    CL2 -->|http://api-2.company.com| T2
    CLX -->|http://api-X.company.com| TX

    %% ---------- Service invocation ----------
    T1 -->|Service invocation| SHARED
    T2 -->|Service invocation| SHARED
    TX -->|Service invocation| SHARED

    %% ---------- Styling ----------
    classDef web fill:#f8d7da,stroke:#e4a0a8,color:#000
    classDef api fill:#d4edda,stroke:#9ecfae,color:#000
    classDef db fill:#cfe2f3,stroke:#9ec1e0,color:#000
    classDef client fill:#fff,stroke:#333,color:#000

    class W1,W2,WX web
    class A1,A2,AX,EMAIL,PUSH,FILES api
    class D1,D2,DX db
    class C1A,C1B,C1C,C2A,C2B,C2C,CXA,CXB,CXC client
```

## Deliverable

Maintenance burden:

- Spring Boot code is used for functionality which is not necessarily core to the mission: email, notifications, files (it seems like reinventing the wheel, and perhaps it is not cost effective)

Suboptimal resource utilization:

- Either app instances are the same but if customer/tenant size/load differ then some of the monolith instances are underutilized, or it leads to maintenance burden to tune each
- Even if instance is tuned to the customer/tenant then API and database loads will still have a different profile and observability will suffer from their cross-talk
- Heavy database queries can starve backend API and colocation of database and backend API make right-sizing impossible
- The diagram indicates no queue (tight coupling) with email & notification services
- Cost grows with number of customers/tenants (while geographically distributed customers could be balanced better; and what if tenants would merge?)

Workforce fragmentation:

- Firefighting on different monolith instances (if tuned specifically to customer)

Coarse-grain scalability:

- What if file storage hits the cap?
- What if the database for one client hits the cap? Scaling for database would also mean scaling the instance for the backend API - is this really necessary?

Single points of failure:

- For a client accessing API there is only one EC2 instance for the monolith
- For the monoliths to access services there is only one EC2 instance for the service

Thus what I would propose:
