import styled from "styled-components";

export const HeaderCompo = () => {
  return (
    <>
      <LogoContainer>
        <LogoTextContainer>
          <LogoText>Para</LogoText>
          <LogoText blue={true}>SOL</LogoText>
        </LogoTextContainer>
        <LogoSubTextContainer>
          <LogoSubText>SBJ 기업연계 (API 단위 테스트)</LogoSubText>
        </LogoSubTextContainer>
      </LogoContainer>
      <SubContainer>
        <SubText>[목표]</SubText>
        <SubText>
          → 뱅킹서비스 제공에 필요한 기능별로 API목록화 하여 정의하고 해당
          목록별 API 서버 서비스를 구현
        </SubText>
        <SubText>
          → API 호출 시 적용 가능한 모든 보안 기능을 검토하여 구현 （인증,
          구간암호화, 이상트래픽 탐지/차단 등）
        </SubText>
        <SubText>
          → 각종 홍보 이벤트 및 거래 Peak일을 감안하여 대량 Transaction을
          효율적으로 소화할수 있는 아키텍쳐를 설계 및 구축
        </SubText>
      </SubContainer>
    </>
  );
};

const LogoContainer = styled.div`
  margin: 2%;
  flex-direction: row;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const LogoText = styled.div<{ blue?: boolean }>`
  color: ${(props) => (props.blue ? "#3182f6" : "black")};
  display: inline-flex;
  font-size: 35px;
  font-weight: bold;
`;
const LogoSubText = styled(LogoText)`
  font-size: 24px;
  text-align: right;
`;
const LogoTextContainer = styled.div`
  flex: auto;
`;
const LogoSubTextContainer = styled.div`
  float: right;
`;
const SubText = styled(LogoText)`
  font-size: 11px;
  margin-bottom: 1%;
`;
const SubContainer = styled(LogoContainer)`
  display: flex;
  flex-direction: column;
  align-items: initial;
`;
