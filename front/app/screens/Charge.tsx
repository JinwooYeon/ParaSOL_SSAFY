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

const Charge = () => {
  const [balance, setBalance] = useState("999,999");
  const [price, setPrice] = useState("0");

  return (
    <LayoutContainer>
      <HeaderContainer>
        <HeaderText>PAY 충전</HeaderText>
        <BalanceBox num={balance} />
      </HeaderContainer>
      <ContentContainer>
        <PriceBox price={price} setPrice={setPrice} />
      </ContentContainer>
      <FooterContainer></FooterContainer>
    </LayoutContainer>
  );
};

export default Charge;
