import styled from "styled-components";
import { Container } from "@mui/material";

export const HeaderCompo = () => {
  return (
    <>
      <LogoContainer>
        <LogoTextContainer>
          <LogoText>Para</LogoText>
          <LogoText blue={true}>SOL</LogoText>
          <LogoTextWith>with SBJ DNX</LogoTextWith>
        </LogoTextContainer>
        {/* <LogoSubTextContainer>
          <LogoSubText>SBJ 기업연계 (API 단위 테스트)</LogoSubText>
        </LogoSubTextContainer> */}
      </LogoContainer>
      <Container maxWidth="md">
        <SubContainer>
          <SubTextMain>
            BaaS 플랫폼 구축을 위한 API 서버 설계 및 구축
          </SubTextMain>
          <SubTextTitle>&lt;사용자 가이드&gt;</SubTextTitle>
          <SubText>
            1. [유저] 아이디 중복확인 후 ParaSOL페이에 회원가입하세요.
          </SubText>
          <SubText>2. 가입된 아이디로 ParaSOL페이에 로그인하세요.</SubText>
          <SubTextDetail>
            - 로그인이 완료되었다면 사용자 인증이 필요한 서비스에 자동으로
            인증토큰값이 입력됩니다.
          </SubTextDetail>
          <SubTextDetail>
            - 비밀번호를 잊으신 경우, 비밀번호 재설정을 통해 임시 비밀번호를
            받으실 수 있습니다.
          </SubTextDetail>
          <SubText>
            3. [인증 및 페이] 은행연결 후 [계좌] 서비스 이용이 가능합니다.
          </SubText>
          <SubTextDetail>- 은행명은 SBJ만 가능합니다.</SubTextDetail>
          <SubTextDetail>
            - 은행연결시 테스트 계정은 다음과 같습니다. id : test, password :
            test
          </SubTextDetail>
        </SubContainer>
      </Container>
    </>
  );
};

const LogoContainer = styled.div`
  margin: 0;
  flex-direction: row;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #3182f6;
`;
const LogoText = styled.div<{ blue?: boolean }>`
  color: ${(props) => (props.blue ? "white" : "navy")};
  display: inline-flex;
  font-size: 35px;
  font-weight: bold;
`;
const LogoTextWith = styled(LogoText)`
  margin-left: 8px;
  color: lightgray;
  font-size: 28px;
  font-weight: normal;
`;
const LogoTextContainer = styled.div`
  /* flex: auto; */
  justify-content: center;
  align-items: center;
  margin: 10px;
`;
// const LogoSubText = styled(LogoText)`
//   color: black;
//   font-size: 24px;
//   text-align: right;
// `;
// const LogoSubTextContainer = styled.div`
//   float: right;
// `;
const SubText = styled(LogoText)`
  color: black;
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 1%;
`;
const SubTextMain = styled(SubText)`
  font-size: 20px;
  margin-bottom: 3%;
`;
const SubTextDetail = styled(SubText)`
  font-weight: normal;
`;
const SubTextTitle = styled(SubText)`
  color: #3182f6;
`;
const SubContainer = styled(LogoContainer)`
  margin: 2%;
  display: flex;
  flex-direction: column;
  align-items: initial;
  background-color: white;
`;
