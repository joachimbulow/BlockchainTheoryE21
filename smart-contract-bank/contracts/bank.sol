pragma solidity ^0.5.0;

contract Bank {

    mapping(address => uint256) private accounts;

    function deposit() public payable returns (uint256) {
        accounts[msg.sender] += msg.value;
        return accounts[msg.sender];
    } 

    function withdraw(uint256 amount) public returns (uint256) {
        require(amount <= accounts[msg.sender]);
        address payable payableMsgSender = address(uint160(address(msg.sender)));
        accounts[msg.sender] -= amount;
        payableMsgSender.transfer(amount);
        return amount;
    }

    function getBalance() public view returns (uint256) {
        return accounts[msg.sender];
    }

}