import { useEffect, useState } from "react";
import BalanceBox from "../components/BalanceBox";
import BtnBox from "../components/BtnBox";
import ConnectedAccountBox from "../components/ConnectedAccountBox";
import PriceBox from "../components/PriceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  ContentFooterContainer,
  BtnContainerRow,
} from "./styled";

interface PropsType {
  balance: string;
  bankInfo: any;
  price: string;
  setPrice: (a: string) => void;
  navigation: any;
  setCharge: (a: boolean) => void;
}

const Pay: React.FC<PropsType> = ({
  balance,
  bankInfo,
  price,
  setPrice,
  navigation,
  setCharge,
}) => {
  const payData = {
    bankInfo,
    price,
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="PAY 충전 / 출금" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <ConnectedAccountBox bankInfo={bankInfo} navigation={navigation} />
          <PriceBox price={price} setPrice={setPrice} />
        </ContentContainer>
        <FooterContainer>
          <BtnContainerRow>
            <BtnBox
              color="white"
              text="출금하기"
              navigation={navigation}
              setCharge={setCharge}
              payData={payData}
            />
            <BtnBox
              color="blue"
              text="충전하기"
              navigation={navigation}
              setCharge={setCharge}
              payData={payData}
            />
          </BtnContainerRow>
          <BtnBox color="red" text="초기화" setPrice={setPrice} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Pay;
