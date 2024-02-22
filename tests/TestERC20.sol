pragma solidity ^0.8.0;

import "hardhat/console.sol";
import { expect } from "chai";
import { ethers } from "hardhat";

describe("ExtendedERC20", function () {
    let ERC20Token;
    let owner;
    let addr1;
    let addr2;

    beforeEach(async function () {
[owner, addr1, addr2] = await ethers.getSigners();
const ERC20TokenFactory = await ethers.getContractFactory("ExtendedERC20");
ERC20Token = await ERC20TokenFactory.deploy("MyToken", "MTK", ethers.utils.parseEther("1000000"));
await ERC20Token.deployed();
});

    it("Should have correct name, symbol, and initial supply", async function () {
expect(await ERC20Token.name()).to.equal("MyToken");
expect(await ERC20Token.symbol()).to.equal("MTK");
expect(await ERC20Token.totalSupply()).to.equal(ethers.utils.parseEther("1000000"));
});

    it("Should allow owner to mint tokens", async function () {
await ERC20Token.connect(owner).mint(addr1.address, ethers.utils.parseEther("1000"));
expect(await ERC20Token.balanceOf(addr1.address)).to.equal(ethers.utils.parseEther("1000"));
});

    it("Should allow token holders to burn tokens", async function () {
await ERC20Token.connect(owner).mint(addr1.address, ethers.utils.parseEther("1000"));
await ERC20Token.connect(addr1).burn(ethers.utils.parseEther("500"));
expect(await ERC20Token.balanceOf(addr1.address)).to.equal(ethers.utils.parseEther("500"));
});
});
