import Verification from "./Verification";

export default class VerificationList {
  public static deepCopy(
    verifications: Verification[],
    serie: string
  ): Verification[] {
    const copyVerifications = [];
    for (let i = 0; i < verifications.length; i++) {
      if (verifications[i].serie === serie) {
        const transCopy = [];
        for (let j = 0; j < verifications[i].transactions.length; j++) {
          const transationCopy = { ...verifications[i].transactions[j] };
          transCopy.push(transationCopy);
        }
        const verCopy = { ...verifications[i], transactions: transCopy };
        copyVerifications.push(verCopy);
      }
    }
    return copyVerifications;
  }

  public static series(verifications: Verification[]): string[] {
    const seriesSet = new Set<string>();
    seriesSet.add("Alla");
    verifications.forEach((ver) => {
      seriesSet.add(ver.serie);
    });
    return Array.from(seriesSet);
  }
}
