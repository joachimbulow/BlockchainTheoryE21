import requests
import json
import base64

print("\t**********************************************")
print("\t***  Hello - Welcome to the Bitcoin test CLI program  ***")
print("\t***  type help to see possible commands  ***")
print("\t**********************************************")

daemon_url = 'http://localhost:18443'

# To execute procedures on the regtest blockchain
def executeRemoteProcedure(payload):
    return requests.post(daemon_url, data=json.dumps(payload), headers={"content-type": "text/plain",
  "Authorization": "Basic " + base64.b64encode("joachimbulow123:myverylongrandompassword12345".encode('ascii')).decode('ascii')})

isDone = False
while not isDone:
    command = input("Enter your command: ")
    if command == "help":
        print("Possible commands:")
        print("help - get help")
        print("balance - check balance")
        print("createaddress - ceate a new address")
        print("sendcoins - send coins to a specific address")
        print("listunspent - list unspent transactions")

    elif command == "balance":
        res = executeRemoteProcedure({
            "jsonrpc": "1.0",
            "method": "getbalance",
            "id": "test",
        })
        print("Your balance is: " + str(json.loads(res.content.decode("utf-8"))["result"]))

    elif command == "createaddress":
        res = executeRemoteProcedure({
            "jsonrpc": "1.0",
            "method": "getnewaddress",
            "id": "test",
        })
        print("The new address is: " + str(json.loads(res.content.decode("utf-8"))["result"]))

    elif command == "sendcoins":
        address = input("Input recipient: ")
        amount = input("Input amount: ")
        res = executeRemoteProcedure({
            "jsonrpc": "1.0",
            "method": "sendtoaddress",
            "id": "test",
            "params": [address, amount]

        })

        print(res.reason)
        if (res.status_code == 200):
            print("Transaction succeeded.")
        else:
            print("Transaction failed.")

    elif command == "listunspent":
        res = executeRemoteProcedure({
            "jsonrpc": "1.0",
            "method": "listunspent",
            "id": "test",
        })
        transactions = json.loads(res.content.decode("utf-8"))["result"]
        print("Number of unspent transactions: " + str(len(transactions)))
        for i in range(len(transactions)):
            print(transactions[i])


    # address: bcrt1qk6cd8qkxwgms0q4st7v4gn5708ena2hj2x5cg8


