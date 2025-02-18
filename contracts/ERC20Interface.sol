pragma solidity ^0.8.0;

interface ERC20Interface {
    function getName() external view returns (string memory);
    function getSymbol() external view returns (string memory);
    function getTotalSupply() external view returns (uint256);
    function getBalanceOf(address account) external view returns (uint256);

    function allowance(address owner, address spender) external view returns (uint256);
    function approve(address spender, uint256 amount) external returns (bool);

    function transfer(address recipient, uint256 amount) external returns (bool);
    function transferFrom(address sender, address recipient, uint256 amount) external returns (bool);

    event Transfer(address indexed from, address indexed to, uint256 value);
    event Approval(address indexed owner, address indexed spender, uint256 value);
}