const { artifacts } = require("hardhat");
const { before, it, describe } = require("mocha");
const { assert } = require("chai");

describe("VotingSystem", () => {
  let VotingSystem;

  before(async () => {
    VotingSystem = await artifacts.require("VotingSystem");
    votingSystem = await VotingSystem.deployed();
  });

  it("should start an election", async () => {
    await votingSystem.startElection("Choosing Car");
    const election = await votingSystem.elections(1);
    assert.equal(election.reason, "Choosing Car");
  });

  it("should add a candidate to the election", async () => {
    await votingSystem.addCandidate(1, "Candidate A");
    const election = await votingSystem.elections(1);
    assert.equal(election.candidatesCount, 1);
    assert.equal(election.candidates[1], "Candidate A");
  });

  it("should allow a voter to vote", async () => {
    await votingSystem.vote(1, 1, { from: accounts[0] });
    const election = await votingSystem.elections(1);
    assert.equal(election.voteCount[1], 1);
  });
});
