# Documentação dos eventos

Aqui se encontram todos os eventos que podem ser enviados e recebidos pelo servidor. Todos os eventos devem seguir o padrão **`JSON`**, e sempre devem conter o campo **`event`** que indica o tipo de evento que está ocorrendo, e o campo **`data`** que contém os dados do evento.

[Voltar ao README.md](../README.md)

</br>

### Entrar no servidor
Evento de requisição
```JSON
{
    "event": "join",
    "data": {
        "name": "Fulano de tal",
        "avatarId": 1
    }
}
```

Evento de resposta
```JSON
{
    "event": "joined",
    "data": {
        "channelId": 2,
        "server": {
            "name": "Servidor do Fulano",
            "connectedClients": [
                {
                    "name": "Fulano de tal",
                    "channelId": 2,
                    "avatarId": 1
                },
                {
                    "name": "Ciclano de tal",
                    "channelId": 3,
                    "avatarId": 2
                }
            ]
        },
    }
}
```

</br>

### Clientes que entram no servidor

Evento de resposta
```JSON
{
    "event": "new_client",
    "data": {
        "name": "Beltrano de tal",
        "channelId": 4,
        "avatarId": 3
    }
}
```

</br>

### Clientes que saem do servidor

Evento de resposta
```JSON
{
    "event": "client_left",
    "data": {
        "channelId": 4
    }
}
```

</br>

### Enviar mensagem

Evento de requisição
```JSON
{
    "event": "send_message",
    "data": {
        "channelId": 1,
        "message": "Olá, mundo!"
    }
}
```

</br>

### Receber mensagens

Evento de resposta
```JSON
{
    "event": "received_message",
    "data": {
        "memberChannelId": 1,
        "targetChannelId": 2,
        "message": "E aí, pessoal, como estão?",
    }
}
```	

### 