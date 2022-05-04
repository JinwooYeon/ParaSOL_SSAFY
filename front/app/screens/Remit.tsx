import { useState } from "react";
import BalanceBox from "../components/BalanceBox";
import PriceBox from "../components/PriceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  HeaderText,
} from "./styled";

const Remit = () => {
  const [balance, setBalance] = useState("999,999");
  const [price, setPrice] = useState("0");

  return (
    <LayoutContainer>
      <HeaderContainer>
        <HeaderText>송금</HeaderText>
        <BalanceBox num={balance} />
      </HeaderContainer>
      <ContentContainer>
        <PriceBox price={price} setPrice={setPrice} />
      </ContentContainer>
      <FooterContainer></FooterContainer>
    </LayoutContainer>
  );
};

export default Remit;
