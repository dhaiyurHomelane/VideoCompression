export interface VideoCompressionPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
