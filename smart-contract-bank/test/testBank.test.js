const Bank = artifacts.require("bank");

//Accounts in callback for testing
contract("Bank", (accounts) => {
    let bank;
    let expectedBalance;
    const ether = 10**18;
    
    before(async() => {
        bank = await Bank.deployed();
    })

    describe("Inserting money and getting balance", async() => {
        before("inserting money with accounts[0] to test functionality", async() => {
            const deposit = 5 * ether;
            await bank.deposit({from: accounts[0], value: deposit});
            expectedBalance = deposit;
        })
        it("actually stores the balance correctly", async() => {
            const balance = await bank.getBalance({from: accounts[0]});
            assert.equal(balance, expectedBalance, "The balances should be equal now.")
        })
        it("Can withdraw money correctly", async() => {
            let bankBalancePreWithdrawal = await bank.getBalance({from: accounts[0]})
            let balancePreWithdrawal = web3.eth.getBalance(accounts[0]);

            await bank.withdraw((2 * ether).toString())

            let bankBalancePostWithdrawal = await bank.getBalance({from: accounts[0]});
            let balancePostWithdrawal = web3.eth.getBalance(accounts[0]);
            assert.notEqual(bankBalancePreWithdrawal, bankBalancePostWithdrawal, "The bank app balances should not be equal now");
            assert.notEqual(balancePreWithdrawal, balancePostWithdrawal, "The ether balances should not be equal now");

        })
    })


})