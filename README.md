# tic-tac-toe
Tic Tac Toe game Restful Server

Uruchomienie: mvnw spring-boot:run 

Gdy uruchomione na localhost:8080  {{root-url}} = http://localhost:8080

**API:**  
Stworzenie nowej gry:  
POST {{root-url}}/game    
Response:    
_{  
    "gameId": "{{gameId}}",  
    "gameUrl": "{{ root-url/relatywny link do stanu gry}}",  
    "playerOneGameToken": "{{ token dla gracza #1 }}",  
    "joinGameUrl": "{{ root-url/relatywny link do dolaczenia do gry}}"  
}_  

Odpytanie o stan gry:  
{{root-url}}/game/{{gameId}}

Dolaczenie przez gracza #2 do gry:  
POST {{root-url}}/game/{{gameId}}/join  

Gra, zaznaczanie kolko/krzyzyk przez graczy:  
POST {{root-url}}/game/{{gameId}}  
z BODY:  
_{  
    "playerToken": "{{playerToken gracza 1 lub 2}}",  
    "col": "a", // columna a lub b lub c  
    "row": 3    // wiersz 1 lub 2 lub 3  
}_  


