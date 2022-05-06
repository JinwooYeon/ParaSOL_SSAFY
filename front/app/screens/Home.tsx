import { useState } from "react";
import BalanceBox from "../components/BalanceBox";
import BtnBox from "../components/BtnBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  ContentFooterContainer,
} from "./styled";

const Home = () => {
  const [balance, setBalance] = useState("999,999");

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer></ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="충전하기" />
          <BtnBox color="blue" text="출금하기" />
          <BtnBox color="blue" text="결제하기" />
          <BtnBox color="blue" text="정보 수정" />
          <BtnBox color="blue" text="수정 완료" />
          <BtnBox color="blue" text="비밀번호 수정" />
          <BtnBox color="red" text="회원 탈퇴" />
          <BtnBox color="red" text="초기화" />
          <BtnBox color="white" text="뒤로" />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
