pragma solidity ^0.8.0;

import "./ERC721Interface.sol";

contract ERC721Token is ERC721Interface {
    uint256 public totalTokens;
    mapping(uint256 => address) private _tokenOwners;
    mapping(address => uint256[]) private _ownedTokens;
    mapping(uint256 => address) private _tokenApprovals;
    mapping(address => mapping(address => bool)) private _operatorApprovals;
    mapping(uint256 => string) private _tokenURIs;

    event Transfer(address indexed from, address indexed to, uint256 indexed tokenId);
    event Approval(address indexed owner, address indexed approved, uint256 indexed tokenId);
    event ApprovalForAll(address indexed owner, address indexed operator, bool approved);

    function balanceOf(address _owner) external view override returns (uint256) {
        return _ownedTokens[_owner].length;
    }

    function ownerOf(uint256 _tokenId) external view override returns (address) {
        return _tokenOwners[_tokenId];
    }

    function safeTransferFrom(address _from, address _to, uint256 _tokenId) external override {
        transferFrom(_from, _to, _tokenId);
    }

    function transferFrom(address _from, address _to, uint256 _tokenId) public override {
        require(_isApprovedOrOwner(msg.sender, _tokenId), "Transfer caller is not owner nor approved");
        require(_from != address(0), "Transfer from the zero address");
        require(_to != address(0), "Transfer to the zero address");

        _transfer(_from, _to, _tokenId);
    }

    function approve(address _approved, uint256 _tokenId) external override {
        address owner = ownerOf(_tokenId);
        require(msg.sender == owner || isApprovedForAll(owner, msg.sender),
            "Approve caller is not owner nor approved for all"
        );

        _tokenApprovals[_tokenId] = _approved;
        emit Approval(msg.sender, _approved, _tokenId);
    }

    function setApprovalForAll(address _operator, bool _approved) external override {
        _operatorApprovals[msg.sender][_operator] = _approved;
        emit ApprovalForAll(msg.sender, _operator, _approved);
    }

    function getApproved(uint256 _tokenId) external view override returns (address) {
        return _tokenApprovals[_tokenId];
    }

    function isApprovedForAll(address _owner, address _operator) external view override returns (bool) {
        return _operatorApprovals[_owner][_operator];
    }

    function mint(address _to, uint256 _tokenId) external override {
        require(_to != address(0), "Mint to the zero address");
        require(_tokenOwners[_tokenId] == address(0), "Token already minted");

        _tokenOwners[_tokenId] = _to;
        _ownedTokens[_to].push(_tokenId);
        totalTokens++;

        emit Transfer(address(0), _to, _tokenId);
    }

    function _transfer(address _from, address _to, uint256 _tokenId) internal {
        require(ownerOf(_tokenId) == _from, "Transfer of token that is not own");
        require(_to != address(0), "Transfer to the zero address");

        _tokenApprovals[_tokenId] = address(0);

        uint256[] storage fromTokens = _ownedTokens[_from];
        for (uint256 i = 0; i < fromTokens.length; i++) {
            if (fromTokens[i] == _tokenId) {
                fromTokens[i] = fromTokens[fromTokens.length - 1];
                fromTokens.pop();
                break;
            }
        }

        _tokenOwners[_tokenId] = _to;
        _ownedTokens[_to].push(_tokenId);

        emit Transfer(_from, _to, _tokenId);
    }

    function _isApprovedOrOwner(address _spender, uint256 _tokenId) internal view returns (bool) {
        address owner = ownerOf(_tokenId);
        return (_spender == owner || _spender == getApproved(_tokenId) || isApprovedForAll(owner, _spender));
    }
}